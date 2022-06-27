package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.tribes.Tribes;
import de.zevyx.iriscore.tribes.kalnas.Kalnas;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
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

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
            Tribes.getKalnas().getPickaxeManager().autosmeltOre(p, e.getBlock().getLocation());
        }
    }


}
