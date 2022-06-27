package de.zevyx.iriscore.api;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DukatenAPI {

    public DukatenAPI() {

    }

    public void setDukaten(Player p, int dukaten) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("UPDATE `players` SET `dukaten` = ? WHERE `uuid` = ?");
            ps.setInt(1, dukaten);
            ps.setString(2, p.getUniqueId().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getDukaten(Player p) {
        try {
            PreparedStatement ps = IrisCore.getInstance().getAPI().getDatabaseAPI().getConnection().prepareStatement("SELECT `dukaten` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("dukaten");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void addDukaten(Player p, int dukaten) {
        setDukaten(p, getDukaten(p) + dukaten);
    }

    public void removeDukaten(Player p, int dukaten) {
        setDukaten(p, getDukaten(p) - dukaten);
    }

    public boolean hasDukaten(Player p, int dukaten) {
        return getDukaten(p) >= dukaten;
    }

}
