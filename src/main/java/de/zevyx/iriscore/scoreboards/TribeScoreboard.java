package de.zevyx.iriscore.scoreboards;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.api.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TribeScoreboard {

    private final Scoreboard scoreboard;
    private final Objective objective;

    public TribeScoreboard() {
        this(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public TribeScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;

        if (this.scoreboard.getObjective("sidebar") != null)
            this.scoreboard.getObjective("sidebar").unregister();

        objective = this.scoreboard.registerNewObjective("sidebar", "dummy", "  §8» §6§lIRIS §r§8«  ");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

   public void setSidebarScore(int slot, String content) {
       Team team = getOrCreateTeam("sidebar" + slot);
       String entry = getEntry(slot);

       if (content == null) {
           scoreboard.resetScores(entry);
           return;
       }
       team.setPrefix(content);
       team.addEntry(entry);

       objective.getScore(entry).setScore(slot);
   }

   public void addPlayer(Player player, String teamName, String prefix, String suffix, ChatColor color) {
       Team team = getOrCreateTeam(teamName);
       team.setPrefix(prefix);
       team.setSuffix(suffix);
       team.setColor(color);
       team.addEntry(player.getName());
   }

    public void removePlayer(Player player) {
       Team team = scoreboard.getEntryTeam(player.getName());
       if (team != null) team.removeEntry(player.getName());
    }



   private Team getOrCreateTeam(String name) {
       Team team = scoreboard.getTeam(name);
       if (team == null) {
           team = scoreboard.registerNewTeam(name);
           team.setPrefix(name);
       }
       return team;
   }

   private String getEntry(int slot) {
        return ChatColor.values()[slot].toString() + ChatColor.values()[slot + 1];
   }

}
