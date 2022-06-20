package de.zevyx.iriscore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class IrisTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("iris")) {
            if(args.length == 1) {
                List<String> arguments = new ArrayList<>();
                arguments.add("admin");
                arguments.add("shop");
                arguments.add("register");

                return arguments;
            }

            if(args[0].equalsIgnoreCase("shop")) {
                List<String> arguments = new ArrayList<>();
                arguments.add("backpack");
                return arguments;
            }

            if(args[0].equalsIgnoreCase("register")) {
                if(cs instanceof Player) {
                    Player p = (Player) cs;
                    if(p.hasPermission("kova.registerplayer")) {
                        List<String> arguments = new ArrayList<>();
                        for(Player player : p.getServer().getOnlinePlayers()) {
                            arguments.add(player.getName());
                        }
                        return arguments;
                    }
                }
            }
        }
        return null;
    }
}
