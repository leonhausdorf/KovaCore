package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.entities.SpecialVex;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IrisCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("iris")) {
            if (cs instanceof Player) {
                Player p = (Player) cs;
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("shop")) {
                        IrisCore.getInstance().getInventoryManager().openBackpackShop(p);
                    }
                    if (args[0].equalsIgnoreCase("stats")) {
                        if (p.hasPermission("kova.stats")) {
                            IrisCore.getInstance().getInventoryManager().openStatsInventory(p);
                        }
                    }
                    if (args[0].equalsIgnoreCase("admin")) {
                        if (p.hasPermission("kova.admin")) {
                            IrisCore.getInstance().getInventoryManager().openAdminInventory(p);
                        }
                    }
                    if (args[0].equalsIgnoreCase("vex")) {
                        if (p.hasPermission("kova.vex")) {
                            SpecialVex vex = new SpecialVex();
                            vex.createVex(p);
                            vex.createVex(p);
                            vex.createVex(p);
                        }
                    }
                }

                if (args.length == 2) {
                    if(args[0].equalsIgnoreCase("portaldest")) {
                        if (p.hasPermission("iris.portal")) {
                            String name = args[1];
                            IrisCore.getInstance().getPortalManager().setPortalDestinationLocation(name, p.getLocation());
                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du hast die Destination für das Portal §e" + name + " §7gesetzt.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("shop")) {
                        if (p.hasPermission("kova.shop")) {
                            if (args[1].equalsIgnoreCase("backpack")) {
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du hast den Backpack Shop erfolgreich platziert!");
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("tpworld")) {
                        if (p.hasPermission("kova.tpworld")) {
                            if (Bukkit.getWorld(args[1]) != null) {
                                p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du wurdest zur Welt §e" + args[1] + "§7 teleportiert!");
                            } else {
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Diese Welt existiert nicht!");
                            }
                        } else {
                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du hast keine Berechtigung für diesen Befehl!");
                        }
                    }
                }

                if(args.length == 3) {
                    if(args[0].equalsIgnoreCase("portal")) {
                        if (p.hasPermission("iris.portal")) {
                            String name = args[1];
                            Integer radius = Integer.parseInt(args[2]);
                            IrisCore.getInstance().getPortalManager().setPortalLocation(name, p.getLocation(), radius);
                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du hast das Portal §e" + name + "§7 erfolgreich platziert!");
                        }
                    }
                }

                if (args.length == 4) {

                    if (args[0].equalsIgnoreCase("tribe")) {
                        if (args[1].equalsIgnoreCase("register")) {
                            if (p.hasPermission("iris.tribe")) {
                                Player target = p.getServer().getPlayer(args[2]);
                                Integer tribe = Integer.parseInt(args[3]);
                                if (target != null) {
                                    if (!IrisCore.getInstance().getPlayerManager().isRegistered(target)) {
                                        if (IrisCore.getInstance().getTribeManager().tribeExists(tribe)) {
                                            IrisCore.getInstance().getPlayerManager().registerPlayer(target, Integer.parseInt(args[3]));
                                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spieler §e" + target.getName() + " §7für den Tribe §e" + IrisCore.getInstance().getTribeManager().getTribeName(tribe) + " §7registriert!");
                                        } else {
                                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cDieser Tribe existiert nicht!");
                                        }
                                    } else {
                                        p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cDieser Spieler ist bereits registriert!");
                                    }
                                } else {
                                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cDer Spieler §e" + args[1] + " §cexistiert nicht!");
                                }
                            }
                        }
                        if (args[1].equalsIgnoreCase("change")) {
                            if (p.hasPermission("iris.tribe")) {
                                Player target = p.getServer().getPlayer(args[2]);
                                Integer tribe = Integer.parseInt(args[3]);
                                if (target != null) {
                                    if (IrisCore.getInstance().getPlayerManager().isRegistered(target)) {
                                        if (IrisCore.getInstance().getTribeManager().tribeExists(tribe)) {
                                            IrisCore.getInstance().getPlayerManager().setTribe(target, Integer.parseInt(args[3]));
                                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast den Spieler §e" + target.getName() + " §7in den Tribe §e" + IrisCore.getInstance().getTribeManager().getTribeName(tribe) + " §7gesetzt!");
                                        } else {
                                            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cDieser Tribe existiert nicht!");
                                        }
                                    } else {
                                        p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Dieser Spieler ist nicht registriert!");
                                        p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Benutze §e/iris tribe register <Spieler> <Tribe> §7um ihn zu registrieren!");
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                cs.sendMessage("§cDu musst ein Spieler sein!");
            }
        }

        return false;
    }
}
