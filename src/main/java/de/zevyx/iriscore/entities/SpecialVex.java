package de.zevyx.iriscore.entities;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SpecialVex implements Listener {

    public void createVex(Player p) {
        Location loc = p.getLocation();
        Integer tribe = IrisCore.getInstance().getPlayerManager().getTribe(p);
        Vex vex = loc.getWorld().spawn(loc.add(0, 2, 0), Vex.class);
        vex.setCustomName(tribe.toString());
        vex.setCustomNameVisible(true);
        Attributable attributable = (Attributable) vex;
        AttributeInstance attributeInstance = attributable.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH);
        attributeInstance.setBaseValue(20);
        vex.setHealth(20);

        ArrayList<Player> targets = new ArrayList<>();
        for(Player all : Bukkit.getOnlinePlayers()) {
            if(IrisCore.getInstance().getPlayerManager().isRegistered(all)) {
                if(IrisCore.getInstance().getPlayerManager().getTribe(all) != tribe) {
                    targets.add(all);
                }
            }
        }

        new BukkitRunnable() {
            Integer count = 0;


            public void run() {
                count++;
                if(!vex.isDead()) {
                    if(count < 30) {
                        if(vex.getTarget() == null) {
                            for (Entity entity : vex.getNearbyEntities(10, 10, 10)) {
                                if (entity instanceof Player) {
                                    if(targets.contains((Player) entity)) {
                                        vex.setTarget((Player) entity);
                                        break;
                                    }

                                }
                            }
                        } else {
                            LivingEntity target = (LivingEntity) vex.getTarget();
                            if(target.isDead()) {
                                vex.setTarget(null);
                            }
                        }
                    } else {
                        vex.remove();
                    }
                } else {
                    cancel();
                }
            }

        }.runTaskTimer(IrisCore.getInstance(), 1, 20L);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent e) {
        if(e.getTarget() instanceof Player) {
            if(e.getEntity() instanceof Vex) {
                if(e.getEntity().getCustomName().equals(IrisCore.getInstance().getPlayerManager().getTribe((Player) e.getTarget()).toString())) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
