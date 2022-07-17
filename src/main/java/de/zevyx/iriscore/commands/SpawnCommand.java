package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.utils.CooldownType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("spawn")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(!IrisCore.getInstance().getCooldownManager().hasCooldown(p.getUniqueId(), CooldownType.COMBAT)) {
                    p.teleport(IrisCore.getInstance().getPortalManager().getPortalDestinationLocation("fwspawn"));
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du wurdest zum Spawn teleportiert.");
                } else {
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du kannst dich während des Kampfes nicht teleportieren.");
                }
            }
        }

        return false;
    }
}
