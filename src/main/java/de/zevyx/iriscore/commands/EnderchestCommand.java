package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("iris.invsee")) {
                if(args.length == 0){
                    p.openInventory(p.getEnderChest());
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cSpieler " + args[1] + " not found!");
                        return false;
                    }
                    p.openInventory(target.getEnderChest());
                } else {
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "§cBitte verwende /enderchest <Spieler>");
                }
            } else {
                p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Dazu hast du keine Rechte!");
            }
        } else {
            sender.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Dazu musst du ein Spieler sein");
        }
        return false;
    }
}
