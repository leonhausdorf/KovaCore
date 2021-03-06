package de.zevyx.iriscore.api;

import de.zevyx.iriscore.utils.EntryName;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public abstract class ScoreboardBuilder {

    protected final Scoreboard scoreboard;
    protected final Objective objective;

    @Getter
    protected final Player player;

    /**
     * Have big performance issues when using this constructor.
     *
     * @deprecated use {@link de.zevyx.iriscore.scoreboards.TribeScoreboard} instead.
     */
    @Deprecated
    public ScoreboardBuilder(Player player, String displayName) {
        this.player = player;

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard()))
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        this.scoreboard = player.getScoreboard();

        if (this.scoreboard.getObjective("display") != null)
            this.scoreboard.getObjective("display").unregister();

        this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        createScoreboard();
    }

    /**
     * Big performance issues when using this constructor.
     *
     * @deprecated use {@link de.zevyx.iriscore.scoreboards.TribeScoreboard} instead.
     */
    @Deprecated
    public abstract void createScoreboard();

    /**
     * Big performance issues when using this constructor.
     *
     * @deprecated use {@link de.zevyx.iriscore.scoreboards.TribeScoreboard} instead.
     */
    @Deprecated
    public abstract void update();

    public void setDisplayName(String displayName) {
        this.objective.setDisplayName(displayName);
    }

    public void setScore(String content, int score) {
        Team team = getTeamByScore(score);

        if (team == null)
            return;

        team.setPrefix(content);
        showScore(score);
    }

    public void removeScore(int score) {
        hideScore(score);
    }

    private EntryName getEntryNameByScore(int id) {
        for (EntryName name : EntryName.values()) {
            if (name.getEntry() == id)
                return name;
        }

        return null;
    }

    private Team getTeamByScore(int id) {
        EntryName name = getEntryNameByScore(id);

        if (name == null)
            return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());

        if (team != null)
            return team;

        team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());

        return team;
    }

    private void showScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if (name == null)
            return;

        if (objective.getScore(name.getEntryName()).isScoreSet())
            return;

        objective.getScore(name.getEntryName()).setScore(score);
    }

    private void hideScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if (name == null)
            return;

        if (!objective.getScore(name.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(name.getEntryName());
    }

}
