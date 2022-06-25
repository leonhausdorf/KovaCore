package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.scoreboards.TribeScoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new TribeScoreboard(e.getPlayer());
    }

}
