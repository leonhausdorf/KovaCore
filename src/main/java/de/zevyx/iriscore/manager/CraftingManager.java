package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingManager {

    public void loadAllRecipes() {
        addBackpackTier1();
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

}
