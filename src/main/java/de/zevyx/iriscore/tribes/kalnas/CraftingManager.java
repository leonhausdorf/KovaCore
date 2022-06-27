package de.zevyx.iriscore.tribes.kalnas;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.ItemBuilderAPI;
import de.zevyx.iriscore.utils.CustomEnchant;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingManager implements Listener {


    public void registerCrafting() {
        addStoneUpgradeRecipe();
        addIronUpgradeRecipe();
        addDiamondUpgradeRecipe();
    }

    public void addStoneUpgradeRecipe() {
        ItemStack hoe = new ItemStack(Material.STONE_PICKAXE);

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "stonepickaxeupgrade");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("S S", " P ", "   ");

        recipe.setIngredient('S', Material.COBBLESTONE);
        recipe.setIngredient('P', Material.WOODEN_PICKAXE);

        Bukkit.addRecipe(recipe);
    }

    public void addIronUpgradeRecipe() {
        ItemStack hoe = new ItemStack(Material.IRON_PICKAXE);

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "ironpickaxeupgrade");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("S S", " P ", "   ");

        recipe.setIngredient('S', Material.IRON_INGOT);
        recipe.setIngredient('P', Material.STONE_PICKAXE);

        Bukkit.addRecipe(recipe);
    }

    public void addDiamondUpgradeRecipe() {
        ItemStack hoe = new ItemStack(Material.DIAMOND_PICKAXE);

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "diapickaxeupgrade");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("S S", " P ", "   ");

        recipe.setIngredient('S', Material.DIAMOND);
        recipe.setIngredient('P', Material.IRON_PICKAXE);

        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(IrisCore.getInstance().getPlayerManager().getTribe(p) == 4) {
            if(e.getRecipe().getResult().getType() == Material.WOODEN_PICKAXE) {
                e.getInventory().setResult(new ItemBuilderAPI(Material.WOODEN_PICKAXE).setLore("§7Autosmelt I").unsafeEnchantItem(CustomEnchant.AUTOSMELT, 1).build());
            }

            if(e.getRecipe().getResult().getType() == Material.STONE_PICKAXE) {
                e.getInventory().setResult(new ItemBuilderAPI(Material.STONE_PICKAXE).setLore("§7Autosmelt I").unsafeEnchantItem(CustomEnchant.AUTOSMELT, 1).build());
            }

            if(e.getRecipe().getResult().getType() == Material.IRON_PICKAXE) {
                e.getInventory().setResult(new ItemBuilderAPI(Material.IRON_PICKAXE).setLore("§7Autosmelt I").unsafeEnchantItem(CustomEnchant.AUTOSMELT, 1).build());
            }

            if(e.getRecipe().getResult().getType() == Material.DIAMOND_PICKAXE) {
                e.getInventory().setResult(new ItemBuilderAPI(Material.DIAMOND_PICKAXE).setLore("§7Autosmelt I").unsafeEnchantItem(CustomEnchant.AUTOSMELT, 1).build());
            }

            if(e.getRecipe().getResult().getType() == Material.GOLDEN_PICKAXE) {
                e.getInventory().setResult(new ItemBuilderAPI(Material.GOLDEN_PICKAXE).setLore("§7Autosmelt I").unsafeEnchantItem(CustomEnchant.AUTOSMELT, 1).build());
            }
        }
    }

    @EventHandler
    public void preCraft(PrepareItemCraftEvent e) {

    }



}
