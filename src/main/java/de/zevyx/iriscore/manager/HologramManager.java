package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.tribes.Tribes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
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
        Player p = e.getPlayer();
        // if(!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return;
        Tribes.getKalnas().getPickaxeManager().autosmeltOre(p, e.getBlock().getLocation());

        if (e.getBlock().getType() == Material.COAL_ORE) {
            if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
                createIndicator(e.getBlock().getLocation(), "§e+25", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 25);
            } else {
                createIndicator(e.getBlock().getLocation(), "§e+20", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 20);
            }
        }

        if (e.getBlock().getType() == Material.IRON_ORE) {
            if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
                createIndicator(e.getBlock().getLocation(), "§e+40", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 40);
            } else {
                createIndicator(e.getBlock().getLocation(), "§e+30", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 30);
            }
        }

        if (e.getBlock().getType() == Material.GOLD_ORE) {
            if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
                createIndicator(e.getBlock().getLocation(), "§e+50", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 50);
            } else {
                createIndicator(e.getBlock().getLocation(), "§e+40", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 40);
            }
        }

        if (e.getBlock().getType() == Material.REDSTONE_ORE) {
            if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
                createIndicator(e.getBlock().getLocation(), "§e+25", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 25);
            } else {
                createIndicator(e.getBlock().getLocation(), "§e+20", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 20);
            }
        }

        if (e.getBlock().getType() == Material.DIAMOND_ORE) {
            if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
                createIndicator(e.getBlock().getLocation(), "§e+100", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 100);
            } else {
                createIndicator(e.getBlock().getLocation(), "§e+80", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 80);
            }
        }

        if (e.getBlock().getType() == Material.EMERALD_ORE) {
            if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
                createIndicator(e.getBlock().getLocation(), "§e+120", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 120);
            } else {
                createIndicator(e.getBlock().getLocation(), "§e+100", 40);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 100);
                IrisAPI.getInstance().getDukatenAPI().addDukaten(e.getPlayer(), 100);
            }
        }

    }


}
