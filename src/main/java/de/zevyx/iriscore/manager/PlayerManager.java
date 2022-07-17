package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.DatabaseAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    public void registerPlayer(Player p, Integer tribe) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO `players` (`uuid`, `name`, `tribeid`) VALUES (?, ?, ?)");
                    ps.setString(1, p.getUniqueId().toString());
                    ps.setString(2, p.getName());
                    ps.setInt(3, tribe);
                    ps.executeUpdate();
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
    }

    public boolean isRegistered(Player p) {

        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `uuid` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, p.getUniqueId().toString());

            return ps.executeQuery().next();
        });
    }

    public Integer getTribe(Player p) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `tribeid` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("tribeid");
            }
            return null;
        });
    }

    public void setTribe(Player p, Integer tribe) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("UPDATE `players` SET `tribeid` = ? WHERE `uuid` = ?");
                    ps.setInt(1, tribe);
                    ps.setString(2, p.getUniqueId().toString());

                    ps.executeUpdate();
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
    }

    public Integer getKills(String uuid) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `kills` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("kills");
            }
            return null;
        });
    }

    public Integer getDeaths(String uuid) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `kills` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("deaths");
            }
            return null;
        });
    }

    public Integer getKD(String p) {
        if (getKills(p) != 0 && getDeaths(p) != 0) {
            return getKills(p) / getDeaths(p);
        }
        return 1;
    }

    public boolean isOnline(Player p) {
        return p.isOnline();
    }

}
