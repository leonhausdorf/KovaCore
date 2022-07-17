package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.scoreboards.TribeScoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {


        IrisCore.getInstance().getTablistManager().setTablis(e.getPlayer());
        IrisCore.getInstance().getTablistManager().setAllPlayerTeams();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {


    }

}
