package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.ItemBuilderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import javax.naming.Name;

public class CraftingManager implements Listener {

    public void loadAllRecipes() {
        addBackpackTier1();
        addCropHoeTier1();
        addCropHoeTier2();

        bonemealInfusedIron();
        addCropHoeTier3();
    }

    private void addBackpackTier1() {
        ItemStack backpack = new ItemStack(Material.BROWN_SHULKER_BOX);
        ItemMeta meta = backpack.getItemMeta();
        meta.setDisplayName("§8» §eBackpack");
        backpack.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "backpack_tier1");
        ShapedRecipe recipe = new ShapedRecipe(key, backpack);

        recipe.shape("SLS", "LIL", "LLL");

        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('I', Material.IRON_INGOT);

        Bukkit.addRecipe(recipe);
    }

    private void addCropHoeTier1() {
        ItemStack hoe = new ItemBuilderAPI(Material.WOODEN_HOE).setName("§8» §eSense §7(§eTier 1§7)").addDamage(1).build();

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "crophoe_1");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("BBB", "BHB", "BBB");

        recipe.setIngredient('B', Material.BONE_MEAL);
        recipe.setIngredient('H', Material.WOODEN_HOE);

        Bukkit.addRecipe(recipe);
    }

    private void addCropHoeTier2() {
        ItemStack hoe = new ItemBuilderAPI(Material.STONE_HOE).setName("§8» §eSense §7(§eTier 2§7)").addDamage(1).build();

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "crophoe_2");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("CCC", "BHB", "BBB");

        recipe.setIngredient('C', Material.COBBLESTONE);
        recipe.setIngredient('B', Material.BONE_MEAL);
        recipe.setIngredient('H', Material.WOODEN_HOE);

        Bukkit.addRecipe(recipe);
    }

    private void bonemealInfusedIron() {
        ItemStack bii = new ItemBuilderAPI(Material.IRON_INGOT).setName("§r§fBonemeal Infused Iron").enchantItem(Enchantment.OXYGEN, 1).addFlag(ItemFlag.HIDE_ENCHANTS).build();

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "bonemealinfusediron");
        ShapedRecipe recipe = new ShapedRecipe(key, bii);

        recipe.shape("BBB", "BIB", "BBB");

        recipe.setIngredient('B', Material.BONE_MEAL);
        recipe.setIngredient('I', Material.IRON_INGOT);

        Bukkit.addRecipe(recipe);
    }

    private void addCropHoeTier3() {
        ItemStack hoe = new ItemBuilderAPI(Material.IRON_HOE).setName("§8» §eSense §7(§eTier 3§7)").addDamage(1).build();

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "crophoe_3");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("III", "BHB", "BBB");

        recipe.setIngredient('B', Material.BONE_MEAL);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('H', Material.STONE_HOE);

        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if(e.getRecipe().getResult().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSense §7(§eTier 2§7)")) {
            ItemStack hoe = e.getInventory().getMatrix()[4];
            if(hoe.getItemMeta() == null || !hoe.getItemMeta().getDisplayName().equals("§8» §eSense §7(§eTier 1§7)")) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }

        if(e.getRecipe().getResult().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSense §7(§eTier 3§7)")) {
            ItemStack[] rc = e.getInventory().getMatrix();
            if(!rc[4].hasItemMeta() || !rc[4].getItemMeta().getDisplayName().equals("§8» §eSense §7(§eTier 2§7)")) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
            if((!rc[0].hasItemMeta() || !rc[0].getItemMeta().getDisplayName().contains("Bonemeal Infused Iron")) && rc[0].getItemMeta().getEnchantLevel(Enchantment.OXYGEN) == 1) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
            if((!rc[1].hasItemMeta() || !rc[1].getItemMeta().getDisplayName().contains("Bonemeal Infused Iron")) && rc[0].getItemMeta().getEnchantLevel(Enchantment.OXYGEN) == 1) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
            if((!rc[2].hasItemMeta() || !rc[2].getItemMeta().getDisplayName().contains("Bonemeal Infused Iron")) && rc[0].getItemMeta().getEnchantLevel(Enchantment.OXYGEN) == 1) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getRecipe().getResult().hasItemMeta()) {
            if(e.getRecipe().getResult().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSense §7(§eTier 1§7)")) {
                if(IrisCore.getInstance().getPlayerManager().getTribe(p) != 3) {
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Das können nur §e" + IrisCore.getInstance().getTribeManager().getTribeName(3) + " §7herstellen");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1, 1);
                    p.closeInventory();
                    e.setCancelled(true);
                }
            }

            if(e.getRecipe().getResult().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSense §7(§eTier 2§7)")) {
                if(IrisCore.getInstance().getPlayerManager().getTribe(p) != 3) {
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Das können nur §e" + IrisCore.getInstance().getTribeManager().getTribeName(3) + " §7herstellen");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1, 1);
                    p.closeInventory();
                    e.setCancelled(true);
                }
            }

            if(e.getRecipe().getResult().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSense §7(§eTier 3§7)")) {
                if(IrisCore.getInstance().getPlayerManager().getTribe(p) != 3) {
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Das können nur §e" + IrisCore.getInstance().getTribeManager().getTribeName(3) + " §7herstellen");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1, 1);
                    p.closeInventory();
                    e.setCancelled(true);
                }
            }

            if(e.getRecipe().getResult().getItemMeta().getDisplayName().equalsIgnoreCase("Bonemeal Infused Iron")) {
                if(IrisCore.getInstance().getPlayerManager().getTribe(p) != 3) {
                    p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Das können nur §e" + IrisCore.getInstance().getTribeManager().getTribeName(3) + " §7herstellen");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1, 1);
                    p.closeInventory();
                    e.setCancelled(true);
                }
            }
        }
    }


}
