package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    public void registerPlayer(Player p, Integer tribe) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("INSERT INTO `players` (`uuid`, `name`, `tribeid`) VALUES (?, ?, ?)");
            ps.setString(1, p.getUniqueId().toString());
            ps.setString(2, p.getName());
            ps.setInt(3, tribe);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRegistered(Player p) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `uuid` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, p.getUniqueId().toString());

            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getTribe(Player p) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `tribeid` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("tribeid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setTribe(Player p, Integer tribe) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("UPDATE `players` SET `tribeid` = ? WHERE `uuid` = ?");
            ps.setInt(1, tribe);
            ps.setString(2, p.getUniqueId().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllBackpacks() {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT * FROM `players`");
            ResultSet rs = ps.executeQuery();

            ArrayList<String> players = new ArrayList<>();
            while(rs.next()) {
                players.add(rs.getString("uuid"));
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getBackpackLevel(String uuid) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `backpacklevel` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("backpacklevel");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setBackpackLevel(String uuid, Integer level) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("UPDATE `players` SET `backpacklevel` = ? WHERE `uuid` = ?");
            ps.setInt(1, level);
            ps.setString(2, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBackpackContents(String uuid) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `backpack` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getString("backpack");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setBackpackContents(String uuid, String data) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("UPDATE `players` SET `backpack` = ? WHERE `uuid` = ?");
            ps.setString(1, data);
            ps.setString(2, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getKills(String uuid) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `kills` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("kills");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Integer getDeaths(String uuid) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `deaths` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("deaths");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Integer getKD(String p) {
        if(getKills(p) != 0 && getDeaths(p) != 0) {
            return getKills(p) / getDeaths(p);
        }
        return 1;
    }

    public boolean isOnline(Player p) {
        return p.isOnline();
    }

}
