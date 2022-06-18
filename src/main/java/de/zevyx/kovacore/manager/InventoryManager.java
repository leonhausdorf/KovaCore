package de.zevyx.kovacore.manager;

import de.zevyx.kovacore.api.InventoryAnimationAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    public void openStatsInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§8Stats");
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);


        new InventoryAnimationAPI(inv, p, fill).addItem(10, new ItemStack(Material.SKELETON_SKULL)).addItem(48, new ItemStack(Material.STONE_BUTTON)).addItem(49, new ItemStack(Material.BARRIER)).addItem(50, new ItemStack(Material.STONE_BUTTON))
                .addItem(11, new ItemStack(Material.SKELETON_SKULL)).addItem(12, new ItemStack(Material.SKELETON_SKULL)).addItem(13, new ItemStack(Material.SKELETON_SKULL)).addItem(14, new ItemStack(Material.SKELETON_SKULL)).addItem(15, new ItemStack(Material.SKELETON_SKULL)).addItem(16, new ItemStack(Material.SKELETON_SKULL))
                .addItem(19, new ItemStack(Material.SKELETON_SKULL)).addItem(20, new ItemStack(Material.SKELETON_SKULL)).addItem(21, new ItemStack(Material.SKELETON_SKULL)).addItem(22, new ItemStack(Material.SKELETON_SKULL)).addItem(23, new ItemStack(Material.SKELETON_SKULL)).addItem(24, new ItemStack(Material.SKELETON_SKULL)).addItem(25, new ItemStack(Material.SKELETON_SKULL))
                .addItem(28, new ItemStack(Material.SKELETON_SKULL)).addItem(29, new ItemStack(Material.SKELETON_SKULL)).addItem(30, new ItemStack(Material.SKELETON_SKULL)).addItem(31, new ItemStack(Material.SKELETON_SKULL)).addItem(32, new ItemStack(Material.SKELETON_SKULL)).addItem(33, new ItemStack(Material.SKELETON_SKULL)).addItem(34, new ItemStack(Material.SKELETON_SKULL))
                .addItem(37, new ItemStack(Material.SKELETON_SKULL)).addItem(38, new ItemStack(Material.SKELETON_SKULL)).addItem(39, new ItemStack(Material.SKELETON_SKULL)).addItem(40, new ItemStack(Material.SKELETON_SKULL)).addItem(41, new ItemStack(Material.SKELETON_SKULL)).addItem(42, new ItemStack(Material.SKELETON_SKULL)).addItem(43, new ItemStack(Material.SKELETON_SKULL))
                .animate();
   }

}
