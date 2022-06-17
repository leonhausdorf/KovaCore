package de.zevyx.kovacore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat("§8" + e.getPlayer().getDisplayName() + " §7» " + e.getMessage().replaceAll("&", "§"));
    }


}
