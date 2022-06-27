package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §eBackpack") && (e.getClick().equals(ClickType.RIGHT) || e.getClick().equals(ClickType.CREATIVE))) {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
            } else if (e.getCursor() != null && e.getCursor().getItemMeta().getDisplayName().equals("§8» §eBackpack")
                    && ((e.getAction().equals(InventoryAction.SWAP_WITH_CURSOR) && e.getClick().equals(ClickType.RIGHT))
                    || (e.getAction().equals(InventoryAction.PLACE_ALL) && e.getClick().equals(ClickType.CREATIVE)))) {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
            }
            if (e.getView().getTitle().equals("§8Admin")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Tribes")) {
                        IrisCore.getInstance().getInventoryManager().openTribeInventory((Player) e.getWhoClicked());
                    }
                }
            }

            if (e.getView().getTitle().equals("§8Backpacks")) {
                e.setCancelled(true);
            }

            if (e.getView().getTitle().equals("Backpack")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §eBackpack")) {
                        e.setCancelled(true);
                        e.getWhoClicked().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du kannst dein Backpack nicht in einem Backpack lagern");
                        ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.BLOCK_ANVIL_LAND, 20, 0.5f);
                    }
                }
            }

            if (e.getView().getTitle().equals("§8Tribes")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cNemokamar")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 1);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMedikirtis")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 2);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFermer")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 3);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kalnas")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 4);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§dAkarier")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 5);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bEceras")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 6);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Maqas")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 7);
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Bedvasis Mirtis")) {
                        IrisCore.getInstance().getInventoryManager().openSingleTribeInventory((Player) e.getWhoClicked(), 8);
                    }

                }
                e.setCancelled(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
