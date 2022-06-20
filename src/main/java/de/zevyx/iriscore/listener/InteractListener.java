package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.utils.Backpack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        try {
            if(e.getItem().hasItemMeta()) {
                if(e.getItem().getItemMeta().getDisplayName().equals("§8» §eBackpack")) {
                    e.setCancelled(true);
                    Backpack backpack = IrisCore.getInstance().getBackpackManager().getBackpack(e.getPlayer().getUniqueId());
                    e.getPlayer().openInventory(backpack.getInventory());
                }
            }
        } catch (NullPointerException ex) {
            // ignore
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) throws InterruptedException {
        Player p = e.getPlayer();
        if(e.getRightClicked() instanceof Villager) {
            if(e.getRightClicked().getCustomName().equals("§8» §eBackpack Shop")) {
                if(p.isSneaking() && p.getInventory().getItemInMainHand().getType() == Material.STICK) {
                    if(p.hasPermission("iris.admin")) {
                        Villager villager = (Villager) e.getRightClicked();
                        villager.remove();
                        p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du hast den Shop erfolgreich gelöscht!");
                    }
                    e.setCancelled(true);
                } else {
                    e.setCancelled(true);
                    Thread.sleep(100);
                    IrisCore.getInstance().getInventoryManager().test(p);
                }
            }
        }
    }

    @EventHandler
    public void inventoryOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        if(e.getInventory().getType() == InventoryType.MERCHANT)
            e.setCancelled(true);
    }

}
