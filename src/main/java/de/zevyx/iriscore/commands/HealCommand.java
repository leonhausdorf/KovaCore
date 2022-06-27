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
            player.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§fDu wurdest von §a" + sender + "§f geheilt!");
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
                    sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§fDu hast §a" + healed + "§f Spieler geheilt!");
                } else if (Bukkit.getPlayer(args[0]) != null) {
                    healPlayer(Bukkit.getPlayer(args[0]), sender.getName());
                    sender.sendMessage(
                            IrisCore.getInstance().getMessageConfig().getPrefix()
                                    + "§fDu hast §a"
                                    + Bukkit.getPlayer(args[0]).getName()
                                    + "§f geheilt!");
                }
            } else if (sender instanceof Player && args.length == 0) {
                healPlayer((Player) sender, "dir");
            } else {
                sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cBitte benutze /heal <Spieler/@all>");
            }
        } else {
            sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cDu hast du keine Berechtigungen");
        }
        return false;
    }
}
