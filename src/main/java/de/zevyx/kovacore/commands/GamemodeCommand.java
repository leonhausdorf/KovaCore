package de.zevyx.kovacore.commands;

import de.zevyx.kovacore.KovaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("gamemode")) {
            if(cs instanceof Player) {
                Player p = (Player) cs;
                if(p.hasPermission("kova.gamemode")) {
                    if(args.length == 1) {
                        if (args[0].equalsIgnoreCase("0")) {
                            p.setGameMode(org.bukkit.GameMode.SURVIVAL);
                            p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eSurvival §7Spielmodus!");
                        } else if (args[0].equalsIgnoreCase("1")) {
                            p.setGameMode(org.bukkit.GameMode.CREATIVE);
                            p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eCreative §7Spielmodus!");
                        } else if (args[0].equalsIgnoreCase("2")) {
                            p.setGameMode(org.bukkit.GameMode.ADVENTURE);
                            p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eAdventure §7Spielmodus!");
                        } else if (args[0].equalsIgnoreCase("3")) {
                            p.setGameMode(org.bukkit.GameMode.SPECTATOR);
                            p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eSpectator §7Spielmodus!");
                        } else {
                            p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Bitte benutze §e/gamemode <0|1|2|3>");
                        }
                    }

                    if(args.length == 2) {
                        String target = args[1];
                        Player t = p.getServer().getPlayer(target);
                        if(t != null) {
                            if (args[0].equalsIgnoreCase("0")) {
                                t.setGameMode(org.bukkit.GameMode.SURVIVAL);
                                p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eSurvival für §e" + t.getName() + " §7gesetzt!");
                            } else if (args[0].equalsIgnoreCase("1")) {
                                t.setGameMode(org.bukkit.GameMode.CREATIVE);
                                p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eCreative für §e" + t.getName() + " §7gesetzt!");
                            } else if (args[0].equalsIgnoreCase("2")) {
                                t.setGameMode(org.bukkit.GameMode.ADVENTURE);
                                p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eAdventure für §e" + t.getName() + " §7gesetzt!");
                            } else if (args[0].equalsIgnoreCase("3")) {
                                t.setGameMode(org.bukkit.GameMode.SPECTATOR);
                                p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eSpectator für §e" + t.getName() + " §7gesetzt!");
                            } else {
                                p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Bitte benutze §e/gamemode <0|1|2|3>");
                            }
                        } else {
                            p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Der Spieler §e" + target + " §7ist nicht online!");
                        }
                    }
                }

            } else {

            }
        }

        return false;
    }


}
