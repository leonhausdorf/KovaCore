package de.zevyx.iriscore.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PortalManager {

    private File file;
    private YamlConfiguration config;
    private final HashMap<String, ArrayList<Player>> range = new HashMap<>();

    public PortalManager() {
        this.file = new File("plugins/IrisCore/portals.yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            save();
        }
    }

    public void setPortalLocation(String name, Location loc, Integer radius) {
        config.set("portals." + name + ".x", loc.getX());
        config.set("portals." + name + ".y", loc.getY());
        config.set("portals." + name + ".z", loc.getZ());
        config.set("portals." + name + ".world", loc.getWorld().getName());
        config.set("portals." + name + ".radius", radius);

        save();
    }

    public Location getPortalLocation(String name) {
        double x = config.getDouble("portals." + name + ".x");
        double y = config.getDouble("portals." + name + ".y");
        double z = config.getDouble("portals." + name + ".z");
        String world = config.getString("portals." + name + ".world");
        int radius = config.getInt("portals." + name + ".radius");
        return new Location(Bukkit.getWorld(world), x, y, z, 0, 0);
    }

    public Integer getPortalRadius(String name) {
        return config.getInt("portals." + name + ".radius");
    }

    public void setPortalDestinationLocation(String name, Location loc) {
        config.set("portals." + name + ".destination.x", loc.getX());
        config.set("portals." + name + ".destination.y", loc.getY());
        config.set("portals." + name + ".destination.z", loc.getZ());
        config.set("portals." + name + ".destination.yaw", loc.getYaw());
        config.set("portals." + name + ".destination.pitch", loc.getPitch());
        config.set("portals." + name + ".destination.world", loc.getWorld().getName());

        save();
    }

    public Location getPortalDestinationLocation(String name) {
        double x = config.getDouble("portals." + name + ".destination.x");
        double y = config.getDouble("portals." + name + ".destination.y");
        double z = config.getDouble("portals." + name + ".destination.z");
        float yaw = (float) config.getDouble("portals." + name + ".destination.yaw");
        float pitch = (float) config.getDouble("portals." + name + ".destination.pitch");
        String world = config.getString("portals." + name + ".destination.world");
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public List<Location> getAllPortalLocations() {
        List<Location> locations = new ArrayList<>();
        for (String name : config.getConfigurationSection("portals").getKeys(false)) {
            double x = config.getDouble("portals." + name + ".x");
            double y = config.getDouble("portals." + name + ".y");
            double z = config.getDouble("portals." + name + ".z");
            String world = config.getString("portals." + name + ".world");
            locations.add(new Location(Bukkit.getWorld(world), x, y, z, 0, 0));
        }
        return locations;
    }

    public List<String> getAllPortalNames() {
        List<String> names = new ArrayList<>();
        for (String name : config.getConfigurationSection("portals").getKeys(false)) {
            names.add(name);
        }
        return names;
    }

    public boolean hasPortalDestinationLocation(String name) {
        return config.contains("portals." + name + ".destination");
    }

    public boolean isPlayerInPortal(String name, Location loc) {
        Location portal = getPortalLocation(name);
        if (portal.getWorld().getName().equals(loc.getWorld().getName())) {
            if (loc.distance(portal) <= getPortalRadius(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerInAnyPortal(Location loc) {
        for (String name : config.getConfigurationSection("portals").getKeys(false)) {
            if (isPlayerInPortal(name, loc)) {
                return true;
            }
        }
        return false;
    }

    public String getNearestPortalName(Location playerLocation) {
        String nearestPortal = "";
        double nearestDistance = Double.MAX_VALUE;
        for (String name : config.getConfigurationSection("portals").getKeys(false)) {
            Location portalLocation = getPortalLocation(name);
            if (portalLocation.getWorld().getName().equals(playerLocation.getWorld().getName())) {
                double distance = playerLocation.distance(portalLocation);
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestPortal = name;
                }
            }
        }
        return nearestPortal;
    }

    public HashMap<String, ArrayList<Player>> getRange() {
        return range;
    }
}
