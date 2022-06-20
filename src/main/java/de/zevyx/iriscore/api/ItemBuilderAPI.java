package de.zevyx.iriscore.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class ItemBuilderAPI {

    private ItemStack is;

    public ItemBuilderAPI(Material m) {
        is = new ItemStack(m);
    }

    public ItemBuilderAPI setAmount(int amount) {
        is.setAmount(amount);
        return this;
    }

    public ItemBuilderAPI setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilderAPI setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilderAPI enchantItem(Enchantment enchantment, Integer level) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(enchantment, level, true);
        is.setItemMeta(im);
        return this;
    }

    public ItemStack build() {
        return is;
    }

    public ItemBuilderAPI setSkullOwner(String uuid) {
        SkullMeta im = (SkullMeta) is.getItemMeta();
        im.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)));
        is.setItemMeta(im);
        return this;
    }
}
