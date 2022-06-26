package de.zevyx.iriscore.commands;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("world")) {
            Player p = (Player) commandSender;
            if(p.hasPermission("iris.world")) {
                if(args.length == 1) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("list");
                    arguments.add("import");
                    arguments.add("remove");
                    arguments.add("tp");
                    return arguments;
                }

                if(args[0].equalsIgnoreCase("import")) {
                    List<String> arguments = new ArrayList<>();
                    return arguments;
                }

                if(args[0].equalsIgnoreCase("remove")) {
                    List<String> arguments = new ArrayList<>(IrisCore.getInstance().getWorldConfig().getWorlds());
                    return arguments;
                }

                if(args[0].equalsIgnoreCase("tp")) {
                    List<String> arguments = new ArrayList<>(IrisCore.getInstance().getWorldConfig().getWorlds());
                    return arguments;
                }
            }
        }
        return null;
    }
}
