package de.zevyx.kovacore.config;

import de.zevyx.kovacore.KovaCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MySQLConfig {

    private File file;
    private YamlConfiguration config;

    public MySQLConfig() {
        this.file = new File("plugins/KovaCore/mysql.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void init() {
        if (!KovaCore.getInstance().getDataFolder().exists()) {
            KovaCore.getInstance().getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

                config.set("host", "localhost");
                config.set("port", "3306");
                config.set("database", "kova");
                config.set("username", "root");
                config.set("password", "1234");

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

    public String getHost() {
        return config.getString("host");
    }

    public String getPort() {
        return config.getString("port");
    }

    public String getDatabase() {
        return config.getString("database");
    }

    public String getUsername() {
        return config.getString("username");
    }

    public String getPassword() {
        return config.getString("password");
    }


}
