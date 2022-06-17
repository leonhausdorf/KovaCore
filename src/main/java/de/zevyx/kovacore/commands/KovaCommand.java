package de.zevyx.kovacore.commands;

import de.zevyx.kovacore.KovaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KovaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("kova")) {
            if(cs instanceof Player) {
                Player p = (Player) cs;
                p.sendMessage(KovaCore.getInstance().getMessageConfig().getPrefix() + "§7Hello World!");
            } else {
                cs.sendMessage("§cDu musst ein Spieler sein!");
            }
        }

        return false;
    }
}
