package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.entities.ShopNPC;
import de.zevyx.iriscore.entities.SpecialVex;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IrisCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("iris")) {
            if(cs instanceof Player) {
                Player p = (Player) cs;
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("shop")) {
                        IrisCore.getInstance().getInventoryManager().openBackpackShop(p);
                    }
                    if (args[0].equalsIgnoreCase("stats")) {
                        if(p.hasPermission("kova.stats")) {
                            IrisCore.getInstance().getInventoryManager().openStatsInventory(p);
                        }
                    }
                    if(args[0].equalsIgnoreCase("admin")) {
                        if(p.hasPermission("kova.admin")) {
                            IrisCore.getInstance().getInventoryManager().openAdminInventory(p);
                        }
                    }
                    if(args[0].equalsIgnoreCase("vex")) {
                        if(p.hasPermission("kova.vex")) {
                            SpecialVex vex = new SpecialVex();
                            vex.createVex(p);
                            vex.createVex(p);
                            vex.createVex(p);
                        }
                    }
                }

                if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("shop")) {
                        if(p.hasPermission("kova.shop")) {
                            if(args[1].equalsIgnoreCase("backpack")) {
                                ShopNPC.spawnBackpackShop(p);
                                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du hast den Backpack Shop erfolgreich platziert!");
                            }
                        }
                    }
                }

                if(args.length == 3) {

                    if (args[0].equalsIgnoreCase("register")) {
                        if(p.hasPermission("kova.registerplayer")) {
                            Player target = p.getServer().getPlayer(args[1]);
                            Integer tribe = Integer.parseInt(args[2]);
                            if(target != null) {
                                if(!IrisCore.getInstance().getPlayerManager().isRegistered(target)) {
                                    if(IrisCore.getInstance().getTribeManager().tribeExists(tribe)) {
                                        IrisCore.getInstance().getPlayerManager().registerPlayer(target, Integer.parseInt(args[2]));
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
                }
            } else {
                cs.sendMessage("§cDu musst ein Spieler sein!");
            }
        }

        return false;
    }
}
