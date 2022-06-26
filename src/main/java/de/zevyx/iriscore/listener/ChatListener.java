package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.utils.CooldownType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String tribePrefix = "§7";
        Player p = e.getPlayer();
        if(IrisCore.getInstance().getPlayerManager().isRegistered(p)) {
            Integer tribe = IrisCore.getInstance().getPlayerManager().getTribe(p);
            if(IrisCore.getInstance().getTribeManager().tribeExists(tribe)) {
                tribePrefix = IrisCore.getInstance().getTribeManager().getTribePrefix(tribe);
            }
        }

        e.setFormat(tribePrefix + e.getPlayer().getDisplayName() + " §8» §7" + e.getMessage().replaceAll("&", "§"));
        IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST);
        if(e.getMessage().equalsIgnoreCase("fuck")) {
            IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST2);
            IrisCore.getInstance().getCooldownManager().addCooldown(p.getUniqueId(), CooldownType.TEST3);
        }
    }


}
