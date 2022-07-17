package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    public static void healPlayer(Player player, String sender) {
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        player.setSaturation(20.0f);
        player.setFoodLevel(20);
        if (sender != null) {
            player.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du wurdest von §e" + sender + "§7 geheilt!");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("@a")
                        || args[0].equalsIgnoreCase("@all")
                        || args[0].equalsIgnoreCase("ALL")) {
                    int healed = 0;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        healPlayer(player, sender.getName());
                        ++healed;
                    }
                    sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast §e" + healed + "§7 Spieler geheilt!");
                } else if (Bukkit.getPlayer(args[0]) != null) {
                    healPlayer(Bukkit.getPlayer(args[0]), sender.getName());
                    sender.sendMessage(
                            IrisCore.getInstance().getMessageConfig().getPrefix()
                                    + "§7Du hast §e"
                                    + Bukkit.getPlayer(args[0]).getName()
                                    + "§7 geheilt!");
                }
            } else if (sender instanceof Player && args.length == 0) {
                healPlayer((Player) sender, "dir");
            } else {
                sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Bitte benutze /heal <Spieler/@all>");
            }
        } else {
            sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§7Du hast du keine Berechtigungen");
        }
        return false;
    }
}
