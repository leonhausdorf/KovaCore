package de.zevyx.kovacore;

import org.bukkit.plugin.java.JavaPlugin;

public class KovaCore extends JavaPlugin {

    private static KovaCore instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }

    public static KovaCore getInstance() {
        return instance;
    }
}
