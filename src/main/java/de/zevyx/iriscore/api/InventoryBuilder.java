package de.zevyx.iriscore.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryBuilder {
    private final HashMap<Integer, ItemStack> items = new HashMap<>();
    private int lines;
    private String name;
    private ItemStack fill;
    private Inventory inventory;

    public static ItemStack getClearMaterial(Material material) {
        return new ItemBuilderAPI(material).setName("§8").build();
    }

    public Inventory build() {
        if (name == null || lines == 0) {
            return null;
        }
        if (inventory == null) {
            inventory = Bukkit.createInventory(null, lines * 9, name);
        }
        if (fill != null) {
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, fill);
            }
        }

        for (Integer integer : items.keySet()) {
            inventory.setItem(integer, items.get(integer));
        }
        return inventory;
    }

    public InventoryBuilder item(int slot, ItemStack item) {
        if (items.containsKey(slot)) {
            items.replace(slot, item);
        } else {
            items.put(slot, item);
        }
        return this;
    }

    public int getLines() {
        return lines;
    }

    public InventoryBuilder lines(int lines) {
        this.lines = lines;
        return this;
    }

    public String getName() {
        return name;
    }

    public InventoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemStack getFill() {
        return fill;
    }

    public InventoryBuilder fill(ItemStack fill) {
        this.fill = fill;
        return this;
    }

    public InventoryBuilder line(int line, ItemStack itemStack) {
        int slot = line * 9;
        for (int i = 0; i < 9; i++) {
            item(slot, itemStack);
            slot++;
        }
        return this;
    }

    public InventoryBuilder verticalLine(int colum, ItemStack itemStack) {
        int slot = colum;
        for (int i = 0; i < lines; i++) {
            item(slot, itemStack);
            slot += 9;
        }
        return this;
    }
}
