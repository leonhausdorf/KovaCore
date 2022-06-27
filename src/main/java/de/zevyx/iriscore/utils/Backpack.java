package de.zevyx.iriscore.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;

import java.io.IOException;
import java.util.UUID;

public class Backpack {

    private final UUID uuid;
    private final Inventory inventory;

    public Backpack(UUID uuid, Integer level) {
        this.uuid = uuid;
        this.inventory = Bukkit.createInventory(null, 9 * level, "Backpack");
    }

    public Backpack(UUID uuid, String base64, Integer level) throws IOException {
        this.uuid = uuid;
        this.inventory = Bukkit.createInventory(null, 9 * level, "Backpack");
        this.inventory.setContents(Base64.itemStackArrayFromBase64(base64));

        ItemStack bundle = new ItemStack(Material.BUNDLE);
        BundleMeta bundleMeta = (BundleMeta) bundle.getItemMeta();

    }

    public UUID getUuid() {
        return uuid;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String toBase64() {
        return Base64.itemStackArrayToBase64(inventory.getContents());
    }

}
