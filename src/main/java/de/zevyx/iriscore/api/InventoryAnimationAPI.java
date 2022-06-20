package de.zevyx.iriscore.api;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class InventoryAnimationAPI {

    private final HashMap<Integer, ItemStack> items = new HashMap<>();
    private Inventory inventory;
    private Player player;
    private ItemStack idleItem;

    public InventoryAnimationAPI(Inventory inventory, Player player, ItemStack idleItem) {
        this.inventory = inventory;
        this.player = player;
        this.idleItem = idleItem;
    }

    public InventoryAnimationAPI addItem(int slot, ItemStack item) {
        items.put(slot, item);
        return this;
    }

    private boolean isItemSet(int slot) {
        return items.containsKey(slot);
    }

    public void animate() {
        Integer inventorySize = inventory.getSize();
        Integer maxSlot = items.size();


        for(int i = 0; i < inventorySize; i++) {
            inventory.setItem(i, idleItem);
        }

        player.openInventory(inventory);

        new BukkitRunnable() {

            Integer count = 0;

            @Override
            public void run() {

                if (inventory.getViewers().size() == 0) this.cancel();

                Object currentKey = items.keySet().toArray()[count];

                inventory.setItem((Integer) currentKey, items.get(currentKey));
                player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 0.1f, 1);

                if (count++ == maxSlot) {
                    this.cancel();
                    items.clear();
                }

            }

        }.runTaskTimer(IrisCore.getInstance(), 4, 2);

    }

}
