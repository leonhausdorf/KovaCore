package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.DatabaseAPI;
import de.zevyx.iriscore.utils.Backpack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class BackpackManager {



    private final Map<UUID, Backpack> map;

    public BackpackManager() {
        map = new HashMap<>();
    }

    public Backpack getBackpack(UUID uuid) {

        if (map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Backpack backpack = new Backpack(uuid, getBackpackLevel(uuid.toString()));

        map.put(uuid, backpack);

        return map.getOrDefault(uuid, new Backpack(uuid, getBackpackLevel(uuid.toString())));
    }

    public void setBackpack(UUID uuid, Backpack backpack) {
        map.put(uuid, backpack);
    }

    public void load() {
        List<String> uuids = getAllBackpacks();

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);
            if (getBackpackContents(s) != null) {
                String base64 = getBackpackContents(s);

                try {
                    map.put(uuid, new Backpack(uuid, base64, getBackpackLevel(uuid.toString())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                map.put(uuid, new Backpack(uuid, getBackpackLevel(uuid.toString())));
            }
        });
    }

    public void reloadBackpack(String uuid) {
        map.remove(uuid);
        if (getBackpackContents(uuid) != null) {
            String base64 = getBackpackContents(uuid);

            try {
                map.put(UUID.fromString(uuid), new Backpack(UUID.fromString(uuid), base64, getBackpackLevel(uuid.toString())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            map.put(UUID.fromString(uuid), new Backpack(UUID.fromString(uuid), getBackpackLevel(uuid.toString())));
        }
    }

    public void save() {
        map.forEach((uuid, backpack) -> {
            if(backpackExists(uuid.toString())) {
                setBackpackContents(uuid.toString(), backpack.toBase64());
            } else {
                addBackpack(uuid.toString());
                setBackpackContents(uuid.toString(), backpack.toBase64());
            }
        });
    }

    public List<String> getAllBackpacks() {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `backpacks`");
            ResultSet rs = ps.executeQuery();


            ArrayList<String> players = new ArrayList<>();
            while (rs.next()) {
                players.add(rs.getString("id"));
            }
            return players;
        });
    }

    public Integer getBackpackLevel(String uuid) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `level` FROM `backpacks` WHERE `id` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("level");
            }
            return null;
        });
    }

    public void setBackpackLevel(String uuid, Integer level) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("UPDATE `backpacks` SET `level` = ? WHERE `id` = ?");
                    ps.setInt(1, level);
                    ps.setString(2, uuid);
                    ps.execute();

                    reloadBackpack(uuid);
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
    }

    public String getBackpackContents(String uuid) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `contents` FROM `backpacks` WHERE `id` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("contents");
            }
            return null;
        });
    }

    public void setBackpackContents(String uuid, String data) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("UPDATE `backpacks` SET `contents` = ? WHERE `id` = ?");
                    ps.setString(1, data);
                    ps.setString(2, uuid);
                    ps.execute();
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
    }

    public boolean backpackExists(String uuid) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM `backpacks` WHERE `id` = ?");
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        return true;
                    }
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
        return false;
    }

    public void addBackpack(String uuid) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO `backpacks` (`id`) VALUES (?)");
                    ps.setString(1, uuid);
                    ps.execute();
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
    }

}
