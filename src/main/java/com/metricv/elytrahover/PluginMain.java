package com.metricv.elytrahover;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        saveDefaultConfig();
        FileConfiguration config = getConfig();

        TickListener listener = new TickListener(getServer(), this);
        listener.runTaskTimer(this, 0, 1);

        CommandHandler handler = new CommandHandler(listener, this);
        getCommand("efly").setExecutor(handler);

        getLogger().info("ElytraHover Enabled.");
    }
}
