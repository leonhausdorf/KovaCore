package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.scoreboards.TribeScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class ScoreboardManager {
    private final Set<TribeScoreboard> tribeScoreboards = new HashSet<>();

    public ScoreboardManager() {
        new BukkitRunnable() {
            public void run() {
                tribeScoreboards.removeIf(tribeScoreboard -> !tribeScoreboard.getPlayer().isOnline());
                tribeScoreboards.forEach(TribeScoreboard::setScores);
            }
        }.runTaskTimer(IrisCore.getInstance(), 20, 20 * 5);
    }

    public void createScoreboard(Player player) {
        tribeScoreboards.add(new TribeScoreboard(player));
    }
}
