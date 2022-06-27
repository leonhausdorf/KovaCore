package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.tribes.Tribes;
import de.zevyx.iriscore.utils.CooldownType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MovementListener implements Listener {

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
}
