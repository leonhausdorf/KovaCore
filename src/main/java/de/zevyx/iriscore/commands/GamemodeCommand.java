package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemode")) {
            if (cs instanceof Player) {
                Player p = (Player) cs;
                if (p.hasPermission("kova.gamemode")) {
                    if (args.length == 1) {
                        switch (args[0].toLowerCase()) {
                            case "0":
                            case "survival":
                                p.setGameMode(org.bukkit.GameMode.SURVIVAL);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eSurvival §7Spielmodus!");
                                break;
                            case "1":
                            case "creative":
                                p.setGameMode(org.bukkit.GameMode.CREATIVE);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eCreative §7Spielmodus!");
                                break;
                            case "2":
                            case "adventure":
                                p.setGameMode(org.bukkit.GameMode.ADVENTURE);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eAdventure §7Spielmodus!");
                                break;
                            case "3":
                            case "spectator":
                                p.setGameMode(org.bukkit.GameMode.SPECTATOR);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du bist nun im §eSpectator §7Spielmodus!");
                                break;
                            default:
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Bitte benutze §e/gamemode <0|1|2|3>");
                                break;
                        }
                    }

                    if (args.length == 2) {
                        String target = args[1];
                        Player t = p.getServer().getPlayer(target);
                        if (t != null) {
                            if (args[0].equalsIgnoreCase("0")) {
                                t.setGameMode(org.bukkit.GameMode.SURVIVAL);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eSurvival für §e" + t.getName() + " §7gesetzt!");
                            } else if (args[0].equalsIgnoreCase("1")) {
                                t.setGameMode(org.bukkit.GameMode.CREATIVE);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eCreative für §e" + t.getName() + " §7gesetzt!");
                            } else if (args[0].equalsIgnoreCase("2")) {
                                t.setGameMode(org.bukkit.GameMode.ADVENTURE);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eAdventure für §e" + t.getName() + " §7gesetzt!");
                            } else if (args[0].equalsIgnoreCase("3")) {
                                t.setGameMode(org.bukkit.GameMode.SPECTATOR);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spielmodus §eSpectator für §e" + t.getName() + " §7gesetzt!");
                            } else {
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Bitte benutze §e/gamemode <0|1|2|3>");
                            }
                        } else {
                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Der Spieler §e" + target + " §7ist nicht online!");
                        }
                    }
                }

            } else {

            }
        }

        return false;
    }

    public List<String> onTabComplete(
            CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp() && !(sender instanceof ConsoleCommandSender)) {
            return null;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("")) {
                List<String> tabComplete = new ArrayList<String>();
                tabComplete.add("0");
                tabComplete.add("1");
                tabComplete.add("2");
                tabComplete.add("3");
                tabComplete.add("survival");
                tabComplete.add("creative");
                tabComplete.add("adventure");
                tabComplete.add("spectator");
                return tabComplete;
            }
            if (args[0].equalsIgnoreCase("s")) {
                List<String> tabComplete = new ArrayList<String>();
                tabComplete.add("survival");
                tabComplete.add("spectator");
                return tabComplete;
            }
            if (args[0].toLowerCase().startsWith("a")) {
                List<String> tabComplete = new ArrayList<String>();
                tabComplete.add("adventure");
                return tabComplete;
            }
            if (args[0].toLowerCase().startsWith("c")) {
                List<String> tabComplete = new ArrayList<String>();
                tabComplete.add("creative");
                return tabComplete;
            }
            if (args[0].toLowerCase().startsWith("su")) {
                List<String> tabComplete = new ArrayList<String>();
                tabComplete.add("survival");
                return tabComplete;
            }
            if (args[0].toLowerCase().startsWith("sp")) {
                List<String> tabComplete = new ArrayList<String>();
                tabComplete.add("spectator");
                return tabComplete;
            }
            return null;
        } else {
            if (args.length == 2) {
                List<String> tabComplete = new ArrayList<String>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    tabComplete.add(player.getDisplayName());
                }
                return tabComplete;
            }
            return null;
        }
    }

}
