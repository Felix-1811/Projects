package dev.felix;

import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }
}