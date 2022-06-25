package de.zevyx.iriscore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        if(e.getItemInHand().getItemMeta() != null) {
            if(e.getItemInHand().getItemMeta().getDisplayName().equals("§8» §eBackpack")) {
                e.setCancelled(true);
            }
        }
    }


}
