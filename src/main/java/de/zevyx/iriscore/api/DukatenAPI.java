package de.zevyx.iriscore.api;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.scoreboards.TribePlayerScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DukatenAPI {

    public DukatenAPI() {

    }

    public void setDukaten(Player p, int dukaten) {
        new BukkitRunnable() {
            public void run() {
                DatabaseAPI.execute(conn -> {
                    PreparedStatement ps = conn.prepareStatement("UPDATE `players` SET `dukaten` = ? WHERE `uuid` = ?");
                    ps.setInt(1, dukaten);
                    ps.setString(2, p.getUniqueId().toString());

                    ps.executeUpdate();
                    return null;
                });
            }
        }.runTaskAsynchronously(IrisCore.getInstance());
    }

    public Integer getDukaten(Player p) {
        return DatabaseAPI.execute(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT `dukaten` FROM `players` WHERE `uuid` = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("dukaten");
            }
            return null;
        });
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
