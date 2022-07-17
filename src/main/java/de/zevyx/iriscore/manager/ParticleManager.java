package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ParticleManager {

    public List<String> names = IrisCore.getInstance().getPortalManager().getAllPortalNames();

    public ParticleManager() {

    }

    public void start() {
        new BukkitRunnable() {


            public void run() {

                for(String name : names) {
                    Location loc = IrisCore.getInstance().getPortalManager().getPortalLocation(name);
                    Bukkit.getWorld(loc.getWorld().getName()).spawnParticle(Particle.PORTAL, loc, 20, 1, 2, 1);
                }

            }

        }.runTaskTimer(IrisCore.getInstance(), 0, 10);
    }

}
