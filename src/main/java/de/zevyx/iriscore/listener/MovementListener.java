package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.tribes.Tribes;
import de.zevyx.iriscore.utils.CooldownType;
import de.zevyx.iriscore.utils.CustomEnchant;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class MovementListener implements Listener {

    private final List<String> locations = IrisCore.getInstance().getPortalManager().getAllPortalNames();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            if(player.getInventory().getBoots() != null && player.getInventory().getBoots().hasItemMeta() && player.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchant.DOUBLEJUMP)) {
                if(player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                    player.setAllowFlight(true);
                }
            }
        }


        if(!IrisCore.getInstance().getPortalManager().isPlayerInAnyPortal(player.getLocation()))
            if(IrisCore.getInstance().getCooldownManager().hasCooldown(player.getUniqueId(), CooldownType.TELEPORTATION))
                IrisCore.getInstance().getCooldownManager().removeCooldown(player.getUniqueId(), CooldownType.TELEPORTATION);

        for(int i = 0; i < locations.size(); i++) {
            if(IrisCore.getInstance().getPortalManager().isPlayerInPortal(locations.get(i), player.getLocation())) {
                if(IrisCore.getInstance().getCooldownManager().hasCooldown(player.getUniqueId(), CooldownType.TELEPORTATION)) {
                    return;
                } else {
                    IrisCore.getInstance().getCooldownManager().addCooldown(player.getUniqueId(), CooldownType.TELEPORTATION);
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if (IrisCore.getInstance().getPlayerManager().getTribe(p) == 5) {
            if (p.getInventory().getHelmet() == null && p.getInventory().getChestplate() == null && p.getInventory().getLeggings() == null && p.getInventory().getBoots() == null) {
                if (IrisCore.getInstance().getCooldownManager().hasCooldown(p.getUniqueId(), CooldownType.AKARIER_INVISIBILITY))
                    return;
                if (e.isSneaking() && !e.getPlayer().isFlying()) {
                    Tribes.getAkarier().getInvisibilityManager().getInvisible().add(p);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1, false, false));
                } else {
                    Tribes.getAkarier().getInvisibilityManager().getInvisible().remove(p);
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }
        }
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.SURVIVAL) {
            e.setCancelled(true);
            if(p.getInventory().getBoots() != null && p.getInventory().getBoots().hasItemMeta() && p.getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchant.DOUBLEJUMP)) {
                if(IrisCore.getInstance().getCooldownManager().hasCooldown(p.getUniqueId(), CooldownType.DOUBLEJUMP)) return;
                Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 2, 0));
                if(b.getType() != Material.AIR) {
                    Vector v = p.getLocation().getDirection().multiply(1).setY(1);
                    p.setVelocity(v);
                    IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.DOUBLEJUMP);
                    p.setAllowFlight(false);
                }
            }
        }
    }

}
