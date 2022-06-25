package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if(e.getView().getTitle().equals("§8Admin")) {
                e.setCancelled(true);
                if(e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Tribes")) {
                        IrisCore.getInstance().getInventoryManager().openTribeInventory((Player) e.getWhoClicked());
                    }
                }
            }

            if(e.getView().getTitle().equals("§8Backpacks")) {
                e.setCancelled(true);
            }

            if(e.getView().getTitle().equals("Backpack")) {
                if(e.getCurrentItem().getItemMeta() != null) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §eBackpack")) {
                        e.setCancelled(true);
                    }
                }
            }

            if(e.getView().getTitle().equals("§8Tribes")) {
                if(e.getCurrentItem().getItemMeta() != null) {
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
