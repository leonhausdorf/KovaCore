package de.zevyx.kovacore.config;

import de.zevyx.kovacore.KovaCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageConfig {

    private File file;
    private YamlConfiguration config;

    public MessageConfig() {
        this.file = new File("plugins/KovaCore/messages.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void init() {
        if (!KovaCore.getInstance().getDataFolder().exists()) {
            KovaCore.getInstance().getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

                config.set("prefix", "&6Kova &8» &7");

                save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage(String key) {
        return config.getString(key).replaceAll("&", "§");
    }

    public String getPrefix() {
        return getMessage("prefix");
    }



}
