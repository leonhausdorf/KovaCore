package de.zevyx.kovacore;

import de.zevyx.kovacore.config.MessageConfig;
import de.zevyx.kovacore.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class KovaCore extends JavaPlugin {

    private static KovaCore instance;

    private MessageConfig messageConfig;

    @Override
    public void onEnable() {
        instance = this;
        messageConfig = new MessageConfig();


        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);


        messageConfig.init();
    }

    @Override
    public void onDisable() {

    }

    public static KovaCore getInstance() {
        return instance;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }
}
