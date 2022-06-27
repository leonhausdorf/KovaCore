package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.utils.CooldownType;
import de.zevyx.iriscore.utils.LocationUtil;
import de.zevyx.iriscore.utils.Trading;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String tribePrefix = "§7";
        Player p = e.getPlayer();
        if (IrisCore.getInstance().getPlayerManager().isRegistered(p)) {
            Integer tribe = IrisCore.getInstance().getPlayerManager().getTribe(p);
            if (IrisCore.getInstance().getTribeManager().tribeExists(tribe)) {
                tribePrefix = IrisCore.getInstance().getTribeManager().getTribePrefix(tribe);
            }
        }
        e.setFormat(tribePrefix + e.getPlayer().getDisplayName() + " §8» §7" + e.getMessage().replaceAll("&", "§"));
        IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST);
        if (e.getMessage().equalsIgnoreCase("fuck")) {
            IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST2);
            IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST3);
            IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST4);
            IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST5);
        }

        if (e.getMessage().equalsIgnoreCase("part")) {
            ArrayList<Location> locations = LocationUtil.generateHelix(e.getPlayer().getLocation(), 0.5, 2);
            locations.forEach(location -> location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location, 1, null));
        }
        if (e.getMessage().equalsIgnoreCase("trade")) {
            Trading trading = new Trading(e.getPlayer(), e.getPlayer());
            p.openInventory(trading.getPlayerInventory());
        }
    }


}
