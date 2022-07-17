package de.zevyx.iriscore.scoreboards;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.IrisAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TribeScoreboardListener implements Listener {

    private final TribePlayerScoreboard tribePlayerScoreboard;

    public TribeScoreboardListener(TribePlayerScoreboard tribePlayerScoreboard) {
        this.tribePlayerScoreboard = tribePlayerScoreboard;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Integer tribeID = IrisCore.getInstance().getPlayerManager().getTribe(p);
        String tribe = IrisCore.getInstance().getTribeManager().getTribeColor(tribeID) + IrisCore.getInstance().getTribeManager().getTribeName(tribeID);

        tribePlayerScoreboard.getScoreboard(p).setSidebarScore(4, "  §8» " + tribe);
        tribePlayerScoreboard.getScoreboard(p).setSidebarScore(1, "  §8» §e" + IrisAPI.getInstance().getDukatenAPI().getDukaten(p));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        tribePlayerScoreboard.remove(e.getPlayer());
        tribePlayerScoreboard.getScoreboards().forEach((uuid, tribeScoreboard) -> {
            tribeScoreboard.removePlayer(e.getPlayer());
        });
    }

}
