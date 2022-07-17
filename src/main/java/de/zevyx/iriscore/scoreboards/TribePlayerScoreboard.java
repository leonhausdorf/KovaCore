package de.zevyx.iriscore.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TribePlayerScoreboard {

    private final Map<UUID, TribeScoreboard> scoreboards = new HashMap<>();
    private final Map<Integer, String> defaults = new HashMap<>();

    public void setSidebarScore(int slot, String content) {
        scoreboards.forEach((uuid, scoreboard) -> scoreboard.setSidebarScore(slot, content));
    }

    public void setDefaultSidebarScore(int slot, String content) {
        setSidebarScore(slot, content);

        if(content == null) defaults.remove(slot);
        else defaults.put(slot, content);
    }

    public void remove(Player player) {
        scoreboards.remove(player.getUniqueId());
    }

    public Map<UUID, TribeScoreboard> getScoreboards() {
        return scoreboards;
    }

    public TribeScoreboard getScoreboard(Player player) {
        return scoreboards.computeIfAbsent(player.getUniqueId(), uuid -> {
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            player.setScoreboard(scoreboard);
            TribeScoreboard tribeScoreboard = new TribeScoreboard(scoreboard);
            defaults.forEach(tribeScoreboard::setSidebarScore);
            return tribeScoreboard;
        });
    }

}
