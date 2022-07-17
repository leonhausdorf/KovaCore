package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistManager {

    public void setTablis(Player player) {
        player.setPlayerListHeaderFooter(" \n    §8» §6§lMINECRAFT IRIS §r§8«    \n ", " \n §7§iWork in progress Beta \n");
    }

    public void setAllPlayerTeams() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            setPlayerTeams(all);
        }
    }

    public void setPlayerTeams(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team nemokamar = scoreboard.getTeam("bnemokamar");

        if(nemokamar == null) {
            nemokamar = scoreboard.registerNewTeam("bnemokamar");
        }

        nemokamar.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(1) + IrisCore.getInstance().getTribeManager().getTribeName(1) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(1));
        nemokamar.setColor(ChatColor.RED);

        Team medikirtis = scoreboard.getTeam("cmedikirtis");

        if(medikirtis == null) {
            medikirtis = scoreboard.registerNewTeam("cmedikirtis");
        }

        medikirtis.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(2) + IrisCore.getInstance().getTribeManager().getTribeName(2) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(2));
        medikirtis.setColor(ChatColor.GREEN);

        Team fermer = scoreboard.getTeam("dfermer");

        if(fermer == null) {
            fermer = scoreboard.registerNewTeam("dfermer");
        }

        fermer.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(3) + IrisCore.getInstance().getTribeManager().getTribeName(3) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(3));
        fermer.setColor(ChatColor.YELLOW);

        Team kalnas = scoreboard.getTeam("ekalnas");

        if(kalnas == null) {
            kalnas = scoreboard.registerNewTeam("ekalnas");
        }

        kalnas.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(4) + IrisCore.getInstance().getTribeManager().getTribeName(4) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(4));
        kalnas.setColor(ChatColor.DARK_GRAY);

        Team akarier = scoreboard.getTeam("fakarier");

        if(akarier == null) {
            akarier = scoreboard.registerNewTeam("fakarier");
        }

        akarier.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(5) + IrisCore.getInstance().getTribeManager().getTribeName(5) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(5));
        akarier.setColor(ChatColor.LIGHT_PURPLE);

        Team eceras = scoreboard.getTeam("geceras");

        if(eceras == null) {
            eceras = scoreboard.registerNewTeam("geceras");
        }

        eceras.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(6) + IrisCore.getInstance().getTribeManager().getTribeName(6) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(6));
        eceras.setColor(ChatColor.AQUA);

        Team magas = scoreboard.getTeam("hmagas");

        if(magas == null) {
            magas = scoreboard.registerNewTeam("hmagas");
        }

        magas.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(7) + IrisCore.getInstance().getTribeManager().getTribeName(7) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(7));
        magas.setColor(ChatColor.GOLD);

        Team mirtis = scoreboard.getTeam("imirtis");

        if(mirtis == null) {
            mirtis = scoreboard.registerNewTeam("imirtis");
        }

        mirtis.setPrefix(IrisCore.getInstance().getTribeManager().getTribeColor(8) + IrisCore.getInstance().getTribeManager().getTribeName(8) + "§8 | " +  IrisCore.getInstance().getTribeManager().getTribeColor(8));
        mirtis.setColor(ChatColor.BLACK);

        Team notribe = scoreboard.getTeam("notribe");

        if(notribe == null) {
            notribe = scoreboard.registerNewTeam("notribe");
        }

        notribe.setPrefix("§7");
        notribe.setColor(ChatColor.GRAY);

        Integer tribe = IrisCore.getInstance().getPlayerManager().getTribe(player);

        if(tribe == 1) {
            nemokamar.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 2) {
            medikirtis.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 3) {
            fermer.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 4) {
            kalnas.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 5) {
            akarier.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 6) {
            eceras.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 7) {
            magas.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else if(tribe == 8) {
            mirtis.addEntry(player.getName());
            notribe.removeEntry(player.getName());
        } else {
            notribe.addEntry(player.getName());
            nemokamar.removeEntry(player.getName());
            medikirtis.removeEntry(player.getName());
            fermer.removeEntry(player.getName());
            kalnas.removeEntry(player.getName());
            akarier.removeEntry(player.getName());
            eceras.removeEntry(player.getName());
            magas.removeEntry(player.getName());
            mirtis.removeEntry(player.getName());
        }

    }

}
