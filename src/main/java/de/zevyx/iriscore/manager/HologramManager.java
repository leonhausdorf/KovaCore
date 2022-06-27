package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.IrisAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class HologramManager implements Listener {

    public void createIndicator(Location location, String display, Integer displayTime) {
        ArmorStand armorStand = location.getWorld().spawn(location.add(0.5, -1.75, 0.5), ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(display);
        armorStand.setCustomNameVisible(true);

        new BukkitRunnable() {

            public void run() {
                armorStand.remove();
            }

        }.runTaskLater(IrisCore.getInstance(), displayTime);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.COBBLESTONE) {
            createIndicator(e.getBlock().getLocation(), "§e+1 Dukat", 40);
            IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 1);
        }
    }


}
