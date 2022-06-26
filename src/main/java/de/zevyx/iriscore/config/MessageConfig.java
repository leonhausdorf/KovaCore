package de.zevyx.iriscore.config;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageConfig {

    private File file;
    private YamlConfiguration config;

    public MessageConfig() {
        this.file = new File("plugins/IrisCore/messages.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void init() {
        if (!IrisCore.getInstance().getDataFolder().exists()) {
            IrisCore.getInstance().getDataFolder().mkdirs();
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
