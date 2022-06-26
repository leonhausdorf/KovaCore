package de.zevyx.iriscore.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorldConfig {

    private File file;
    private YamlConfiguration config;

    public WorldConfig() {
        this.file = new File("plugins/IrisCore/worlds.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        if (!file.exists()) {
            try {
                file.createNewFile();

                List<String> worlds = new ArrayList<>();
                worlds.add("world");

                config.set("worlds", worlds);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            save();
        }
    }

    public List<String> getWorlds() {
        return (List<java.lang.String>) config.getList("worlds");
    }

    public void addWorld(String world) {
        List<String> worlds = getWorlds();
        worlds.add(world);
        config.set("worlds", worlds);
        save();
    }

    public void removeWorld(String world) {
        List<String> worlds = getWorlds();
        worlds.remove(world);
        config.set("worlds", worlds);
        save();
    }

    public boolean hasWorld(String world) {
        return config.getList("worlds").contains(world);
    }

}
