package com.metricv.elytrahover;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler implements CommandExecutor {
    private final TickListener listener;
    private final JavaPlugin plugin;

    CommandHandler(TickListener listener, JavaPlugin plugin) {
        this.listener = listener;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender_x, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        if(sender_x instanceof Player) {
            Player sender = (Player) sender_x;
            if(args.length <= 0) return false;
            switch (args[0]) {
                case "on":
                    if (!sender.hasPermission("elytrahover.toggle")) {
                        sender.sendMessage(
                            config.getString("message.NoTogglePerm",
                                "Error. message.NoTogglePerm not found"));
                        plugin.getLogger().info(String.valueOf(sender.hasPermission("elytrahover.toggle")));
                        plugin.getLogger().info(String.valueOf(sender.isPermissionSet("elytrahover.toggle")));
                    } else if (sender.getInventory().getChestplate() != null
                        && sender.getInventory().getChestplate().getType() == Material.ELYTRA) {
                        listener.addFlyer(sender);
                        sender.sendMessage(
                            config.getString("message.FlyingOn", "Error. message.FlyingOn not found."));
                    } else {
                        sender.sendMessage(
                            config.getString("message.OnWithoutElytra",
                                "Error. message.OnWithoutElytra not found."));
                    }
                    return true;
                case "off":
                    if (!sender.hasPermission("elytrahover.toggle"))
                        sender.sendMessage(
                            config.getString("message.NoTogglePerm",
                                "Error. message.NoTogglePerm not found"));
                    else {
                        listener.removeFlyer(sender);
                        sender.sendMessage(
                            config.getString("message.FlyingOff",
                                "message.FlyingOff not found."));
                    }
                    return true;
                case "reload":
                    if (sender.hasPermission("elytrahover.manage") || sender.isOp()) {
                        plugin.reloadConfig();
                        sender.sendMessage(
                            config.getString("message.ConfigReloaded",
                                "Error. message.ConfigReloaded not found."));
                    } else {
                        sender.sendMessage(
                            config.getString("message.NoManagePerm",
                                "Error. message.NoManagePerm not found"));
                    }
                    return true;
                default:
                    return false;
            }
        } else {
            sender_x.sendMessage("Server consoles cannot fly, even with elytra.");
            return false;
        }
    }
}
