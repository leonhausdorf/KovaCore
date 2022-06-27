package de.zevyx.iriscore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AddEnchantingCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("minecraft.command.enchant")) {
            return false;
        }
        if (args.length != 2) {
            player.sendMessage("/addenchanting <Command> <Level>");
            return false;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) {
            return false;
        }
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        Enchantment enchantment = getByName(args[0]);
        if (enchantment == null) {
            player.sendMessage("Enchantment not found");
            return false;
        }
        if (itemMeta.hasEnchant(enchantment)) {
            player.sendMessage("The item is already enchanted");
            return false;
        }
        int level = -1;
        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            player.sendMessage("No available Number!");
            return false;
        }
        itemMeta.addEnchant(enchantment, level, true);
        item.setItemMeta(itemMeta);
        player.sendMessage("§aSuccessful enchanted!");
        return false;
    }

    private Enchantment getByName(String name) {
        for (Enchantment value : Enchantment.values()) {
            if (value.getKey().getKey().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("minecraft.command.enchant")) {
            return null;
        }
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            if (args[args.length - 1].equals("")) {
                for (Enchantment value : Enchantment.values()) {
                    list.add(value.getKey().getKey());
                }
            } else {
                for (Enchantment value : Enchantment.values()) {
                    if (value.getKey().getKey().toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
                        list.add(value.getKey().getKey());
                    }
                }
            }

        }
        return list;
    }
}
