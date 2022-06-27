package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TribeManager {

    public boolean tribeExists(Integer tribeID) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `id` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTribeName(Integer tribeID) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `name` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getTribePrefix(Integer tribeID) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `color` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("color") + getTribeName(tribeID) + " §8| §" + rs.getString("color");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getTribeColor(Integer tribeID) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `color` FROM `tribes` WHERE `id` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("color");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<String> getTribeMembers(Integer tribeID) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `uuid` FROM `players` WHERE `tribeid` = ?");
            ps.setInt(1, tribeID);

            ResultSet rs = ps.executeQuery();
            ArrayList<String> players = new ArrayList<>();
            while (rs.next()) {
                players.add(rs.getString("uuid"));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
