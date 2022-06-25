package de.zevyx.iriscore.commands;

import org.bukkit.Bukkit;
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
                arguments.add("tribe");
                arguments.add("tpworld");

                return arguments;
            }

            if(args[0].equalsIgnoreCase("shop")) {
                List<String> arguments = new ArrayList<>();
                arguments.add("backpack");
                return arguments;
            }

            if(args[0].equalsIgnoreCase("tpworld")) {
                List<String> arguments = new ArrayList<>();
                Bukkit.getWorlds().forEach(world -> arguments.add(world.getName()));
            }

            if(args[0].equalsIgnoreCase("tribe")) {
                List<String> arguments = new ArrayList<>();
                arguments.add("register");
                arguments.add("change");

                return arguments;
            }

            if(args[1].equalsIgnoreCase("register")) {
                if(cs instanceof Player) {
                    Player p = (Player) cs;
                    if(p.hasPermission("iris.tribe")) {
                        List<String> arguments = new ArrayList<>();
                        for(Player player : p.getServer().getOnlinePlayers()) {
                            arguments.add(player.getName());
                        }
                        return arguments;
                    }
                }
            }

            if(args[1].equalsIgnoreCase("change")) {
                if(cs instanceof Player) {
                    Player p = (Player) cs;
                    if(p.hasPermission("iris.tribe")) {
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
