package com.metricv.elytrahover;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class TickListener extends BukkitRunnable {
    Server server;
    private final Map<Player, Integer> flyCount;
    private final Set<Player> warned;
    JavaPlugin plugin;
    SecureRandom rnd = new SecureRandom();

    TickListener(Server svr, JavaPlugin plugin) {
        this.server = svr;
        this.flyCount = new HashMap<>();
        this.plugin = plugin;
        this.warned = new HashSet<>();
    }

    @Override
    public void run() {
        FileConfiguration conf = plugin.getConfig();
        server.getOnlinePlayers().forEach(p -> {
            if((p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE)
                    && flyCount.containsKey(p) && p.isFlying()) {
                if(p.getInventory().getChestplate() != null
                      && p.getInventory().getChestplate().getType() == Material.ELYTRA
                      && ((Damageable)p.getInventory().getChestplate().getItemMeta()).getDamage() < Material.ELYTRA.getMaxDurability()) {
                    flyCount.put(p, flyCount.get(p) + 1);
                    if(flyCount.get(p) > conf.getInt("TickPerDamage", 10)) {
                        if(!damageElytra(p.getInventory().getChestplate())) {
                            p.sendMessage(conf.getString("message.DurabilityOut", "Error: message.DurabilityOut undefined."));
                            p.setAllowFlight(false);
                            p.setFlying(false);
                            flyCount.remove(p);
                            warned.remove(p);
                        }
                        flyCount.put(p, flyCount.get(p) - conf.getInt("TickPerDamage", 10));
                    }

                    if(((Damageable)p.getInventory().getChestplate().getItemMeta()).getDamage()
                        >= 0.9 * Material.ELYTRA.getMaxDurability()) {
                        if(!warned.contains(p)) {
                            p.sendMessage(conf.getString("message.warning", "Error: message.warning not found."));
                            warned.add(p);
                        }
                    }
                } else {
                    p.sendMessage(conf.getString("message.noelytra","You lost your elytra. Flying will be disabled immediately."));
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    flyCount.remove(p);
                    warned.remove(p);
                }
            } else warned.remove(p);
        });
    }

    public void addFlyer(Player p) {
        flyCount.put(p, 0);
        p.setAllowFlight(true);
    }

    public void removeFlyer(Player p) {
        flyCount.remove(p);
        p.setAllowFlight(false);
        p.setFlying(false);
        warned.remove(p);
    }

    private boolean damageElytra(ItemStack elytra) {
        int durabilityLevel = elytra.getEnchantments().getOrDefault(Enchantment.DURABILITY, 0);
        double chance = (1.0/(durabilityLevel+1));
        double rand = rnd.nextDouble();
        if(rand < chance) {
            Damageable damageable = (Damageable) elytra.getItemMeta();
            damageable.setDamage(damageable.getDamage() + 1);
            elytra.setItemMeta((ItemMeta) damageable);
            return damageable.getDamage() < elytra.getType().getMaxDurability();
        }
        return true;
    }
}
