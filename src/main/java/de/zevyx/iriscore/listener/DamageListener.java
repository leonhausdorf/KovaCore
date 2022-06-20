package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.entities.CorpseEntity;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(e.getFinalDamage() >= p.getHealth()) {
                e.setCancelled(false);
            }
        }
    }

}
