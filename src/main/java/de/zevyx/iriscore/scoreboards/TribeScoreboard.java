package de.zevyx.iriscore.scoreboards;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.api.ScoreboardBuilder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TribeScoreboard extends ScoreboardBuilder {

    public TribeScoreboard(Player player) {
        super(player, "  §8» §6§lIRIS §r§8«  ");
    }

    @Override
    public void createScoreboard() {
        Integer tribeID = IrisCore.getInstance().getPlayerManager().getTribe(player);

        setScore(" ", 7);
        setScore("§7Dein Tribe", 6);
        setScore("  §8» " + IrisCore.getInstance().getTribeManager().getTribeColor(tribeID) + IrisCore.getInstance().getTribeManager().getTribeName(tribeID), 5);
        setScore("  ", 4);
        setScore("§7Dukaten", 3);
        setScore("  §8» §e" + IrisAPI.getInstance().getDukatenAPI().getDukaten(player), 2);
        setScore("     ", 1);
        setScore("     ", 0);
    }

    @Override
    public void update() {
        Integer tribeID = IrisCore.getInstance().getPlayerManager().getTribe(player);

        setScore("  §8» " + IrisCore.getInstance().getTribeManager().getTribeColor(tribeID) + IrisCore.getInstance().getTribeManager().getTribeName(tribeID), 5);
        setScore("  §8» §e" + IrisAPI.getInstance().getDukatenAPI().getDukaten(player), 2);
    }

}
