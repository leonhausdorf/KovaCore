package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.DatabaseAPI;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TribeManager {

    public boolean tribeExists(Integer tribeID) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `id` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            return ps.executeQuery().next();
        });
    }

    public String getTribeName(Integer tribeID) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `name` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
            return null;
        });
    }

    public String getTribePrefix(Integer tribeID) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `color` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("color") + getTribeName(tribeID) + " §8| §" + rs.getString("color");
            }
            return null;
        });
    }

    public String getTribeColor(Integer tribeID) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `color` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("color");
            }
            return null;
        });
    }

    public ArrayList<String> getTribeMembers(Integer tribeID) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `uuid` FROM `players` WHERE `tribeid` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();
            ArrayList<String> players = new ArrayList<>();
            while (rs.next()) {
                players.add(rs.getString("uuid"));
            }
            return players;
        });
    }

}
