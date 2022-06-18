package de.zevyx.kovacore;

import de.zevyx.kovacore.api.KovaAPI;
import de.zevyx.kovacore.commands.GamemodeCommand;
import de.zevyx.kovacore.commands.KovaCommand;
import de.zevyx.kovacore.config.MessageConfig;
import de.zevyx.kovacore.config.MySQLConfig;
import de.zevyx.kovacore.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class KovaCore extends JavaPlugin {

    private static KovaCore instance;

    private MessageConfig messageConfig;
    private MySQLConfig mySQLConfig;

    @Override
    public void onEnable() {
        instance = this;
        messageConfig = new MessageConfig();
        mySQLConfig = new MySQLConfig();


        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        getCommand("kova").setExecutor(new KovaCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());

        messageConfig.init();
        mySQLConfig.init();

        KovaAPI.getInstance().getDatabaseAPI().connect();
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

    public MySQLConfig getMySQLConfig() {
        return mySQLConfig;
    }
}
