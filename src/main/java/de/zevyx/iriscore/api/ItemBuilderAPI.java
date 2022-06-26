package de.zevyx.iriscore.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
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

    public ItemBuilderAPI(ItemStack i) {
        is = i;
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

    public ItemBuilderAPI addDamage(double damage) {
        ItemMeta im = is.getItemMeta();
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackdamage", damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilderAPI removeDurability(double amount) {
        short minus = (short) amount;
        if(is.getDurability() + minus > is.getType().getMaxDurability()) {
            is.setAmount(0);
        } else {
            is.setDurability((short) (is.getDurability() + minus));
        }
        return this;
    }

    public ItemBuilderAPI addFlag(ItemFlag flag) {
        ItemMeta im = is.getItemMeta();
        im.addItemFlags(flag);
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
    public ItemBuilderAPI setSkullOwner(Player player) {
        SkullMeta im = (SkullMeta) is.getItemMeta();
        im.setOwningPlayer(player);
        is.setItemMeta(im);
        return this;
    }
}
