package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.ItemBuilderAPI;
import de.zevyx.iriscore.tribes.PortableIronGolem;
import de.zevyx.iriscore.utils.Backpack;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        try {
            if (e.getItem().hasItemMeta()) {
                ItemMeta itemMeta = e.getItem().getItemMeta();
                if (itemMeta.getDisplayName().contains("§8» §eBackpack")) {
                    e.setCancelled(true);
                    List<String> lore = itemMeta.getLore();
                    if (lore.isEmpty()) {
                        return;
                    }
                    String backpackID = lore.get(0).replaceAll("§8BackpackID: ", "");
                    UUID backpackUuid = null;
                    try {
                        backpackUuid = UUID.fromString(backpackID);
                    } catch (Exception ex) {
                        return;
                    }
                    Backpack backpack = IrisCore.getInstance().getBackpackManager().getBackpack(backpackUuid);
                    e.getPlayer().openInventory(backpack.getInventory());
                }
            }

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_HOE) {
                    BlockData bd = e.getClickedBlock().getBlockData();
                    if (bd instanceof Ageable) {
                        Ageable ageable = (Ageable) bd;
                        if (ageable.getAge() != ageable.getMaximumAge()) {
                            ageable.setAge(ageable.getAge() + 1);
                            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.BONE_MEAL_USE, 0);
                            short minus = 1;
                            e.getItem().setDurability((short) (e.getItem().getDurability() + minus));
                            e.getClickedBlock().setBlockData(ageable);
                        }
                    }
                }
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.STONE_HOE) {
                    BlockData bd = e.getClickedBlock().getBlockData();
                    if (bd instanceof Ageable) {
                        Ageable ageable = (Ageable) bd;
                        if (ageable.getAge() != ageable.getMaximumAge()) {
                            if (ageable.getAge() + 2 > ageable.getMaximumAge()) {
                                ageable.setAge(ageable.getMaximumAge());
                            } else {
                                ageable.setAge(ageable.getAge() + 2);
                            }
                            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.BONE_MEAL_USE, 0);
                            e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                            e.getClickedBlock().setBlockData(ageable);
                        }
                    }
                }
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_HOE) {
                    BlockData bd = e.getClickedBlock().getBlockData();
                    if (bd instanceof Ageable) {
                        Ageable ageable = (Ageable) bd;
                        if (ageable.getAge() != ageable.getMaximumAge()) {
                            if (ageable.getAge() + 3 > ageable.getMaximumAge()) {
                                ageable.setAge(ageable.getMaximumAge());
                            } else {
                                ageable.setAge(ageable.getAge() + 3);
                            }
                            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.BONE_MEAL_USE, 0);
                            e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                            e.getClickedBlock().setBlockData(ageable);
                        }
                    }
                }
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE) {
                    BlockData bd = e.getClickedBlock().getBlockData();
                    if (bd instanceof Ageable) {
                        Ageable ageable = (Ageable) bd;
                        if (ageable.getAge() != ageable.getMaximumAge()) {
                            ageable.setAge(ageable.getMaximumAge());
                            e.getClickedBlock().getLocation().getWorld().playEffect(e.getClickedBlock().getLocation(), Effect.BONE_MEAL_USE, 0);
                            e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                            e.getClickedBlock().setBlockData(ageable);
                        }
                    }
                }
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_HOE) {

                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            Block b = e.getPlayer().getWorld().getBlockAt(e.getClickedBlock().getX() + x, e.getClickedBlock().getY(), e.getClickedBlock().getZ() + z);
                            BlockData bd = b.getBlockData();
                            if (bd instanceof Ageable) {
                                Ageable ageable = (Ageable) bd;
                                if (ageable.getAge() != ageable.getMaximumAge()) {
                                    ageable.setAge(ageable.getMaximumAge());
                                    b.getLocation().getWorld().playEffect(b.getLocation(), Effect.BONE_MEAL_USE, 0);
                                    e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                                    b.setBlockData(ageable);
                                }
                            }
                        }
                    }
                }
            }

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.DIRT || e.getClickedBlock().getType() == Material.GRASS_BLOCK)) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_HOE) {
                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            Block b = e.getPlayer().getWorld().getBlockAt(e.getClickedBlock().getX() + x, e.getClickedBlock().getY(), e.getClickedBlock().getZ() + z);
                            if (b.getType() == Material.DIRT || b.getType() == Material.GRASS_BLOCK)
                                if (b.getLocation().add(0, 1, 0).getBlock().getType() == Material.AIR) {
                                    b.setType(Material.FARMLAND);
                                    e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                                }
                        }
                    }
                }

                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE) {
                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            Block b = e.getPlayer().getWorld().getBlockAt(e.getClickedBlock().getX() + x, e.getClickedBlock().getY(), e.getClickedBlock().getZ() + z);
                            if (b.getType() == Material.DIRT || b.getType() == Material.GRASS_BLOCK)
                                if (b.getLocation().add(0, 1, 0).getBlock().getType() == Material.AIR) {
                                    b.setType(Material.FARMLAND);
                                    e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                                }
                        }
                    }
                }

                if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_HOE) {
                    for (int x = -2; x <= 2; x++) {
                        for (int z = -2; z <= 2; z++) {
                            Block b = e.getPlayer().getWorld().getBlockAt(e.getClickedBlock().getX() + x, e.getClickedBlock().getY(), e.getClickedBlock().getZ() + z);
                            if (b.getType() == Material.DIRT || b.getType() == Material.GRASS_BLOCK)
                                if (b.getLocation().add(0, 1, 0).getBlock().getType() == Material.AIR) {
                                    b.setType(Material.FARMLAND);
                                    e.getPlayer().getInventory().setItemInMainHand(new ItemBuilderAPI(e.getPlayer().getInventory().getItemInMainHand()).removeDurability(1).build());
                                }
                        }
                    }
                }
            }

        } catch (NullPointerException ex) {
            // ignore
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) throws InterruptedException {
        Player p = e.getPlayer();
        if (e.getRightClicked() instanceof IronGolem && p.isSneaking()) {
            p.getInventory().addItem(PortableIronGolem.getItemFromIronGolem((IronGolem) e.getRightClicked()));
            e.getRightClicked().remove();
        }
        if (e.getRightClicked() instanceof Villager) {
            if (e.getRightClicked().getCustomName().equals("§8» §eBackpack Shop")) {
                if (p.isSneaking() && p.getInventory().getItemInMainHand().getType() == Material.STICK) {
                    if (p.hasPermission("iris.admin")) {
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
        if (e.getInventory().getType() == InventoryType.MERCHANT)
            e.setCancelled(true);
    }

}
