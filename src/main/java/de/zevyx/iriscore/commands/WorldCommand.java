package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("world")) {
            if (cs instanceof Player) {
                Player p = (Player) cs;
                if (p.hasPermission("iris.world")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("list")) {
                            cs.sendMessage("§8» §eDiese Welten sind geladen §8«");
                            cs.sendMessage(" ");
                            IrisCore.getInstance().getWorldConfig().getWorlds().forEach(world -> cs.sendMessage("§7" + world));
                            cs.sendMessage(" ");
                            cs.sendMessage("§8» §eDiese Welten sind geladen §8«");
                        }
                    }

                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("import")) {
                            if (!IrisCore.getInstance().getWorldConfig().hasWorld(args[1])) {
                                Bukkit.broadcastMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Aktuell wird eine Welt importiert. Es kann zu lags kommen.");
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Importiere die Welt §e" + args[1]);
                                if (IrisCore.getInstance().getWorldManager().addNewWorld(args[1]))
                                    cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Die Welt §e" + args[1] + " §7wurde erfolgreich importiert.");
                                else
                                    cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Die Welt §e" + args[1] + " §7konnte nicht importiert werden.");
                                Bukkit.broadcastMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Weltenimport abgeschlossen!");
                            } else {
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Die Welt §e" + args[1] + " §7ist bereits geladen!");
                            }
                        }

                        if (args[0].equalsIgnoreCase("remove")) {
                            if (IrisCore.getInstance().getWorldConfig().hasWorld(args[1])) {
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Die Welt §e" + args[1] + " §7wird entfernt.");
                                IrisCore.getInstance().getWorldManager().removeWorld(args[1]);
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Die Welt §e" + args[1] + " §7wurde erfolgreich entfernt.");
                            } else {
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Die Welt §e" + args[1] + " §7existiert nicht!");
                            }
                        }

                        if (args[0].equalsIgnoreCase("tp")) {
                            if (IrisCore.getInstance().getWorldConfig().hasWorld(args[1])) {
                                p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du wurdest zur Welt §e" + args[1] + "§7 teleportiert!");
                            } else {
                                cs.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Diese Welt existiert nicht!");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
