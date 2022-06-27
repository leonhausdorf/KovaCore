package de.zevyx.iriscore.tribes.akarier;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.ItemBuilderAPI;
import de.zevyx.iriscore.utils.CustomEnchant;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingManager implements Listener {

    public void registerCrafting() {
        doubleJumpBoots();
    }

    public void doubleJumpBoots() {
        ItemStack hoe = new ItemBuilderAPI(Material.IRON_BOOTS).setName("§8» §eDouble Jumpers").unsafeEnchantItem(CustomEnchant.DOUBLEJUMP, 1).setLore("§7DoubleJump I").build();

        NamespacedKey key = new NamespacedKey(IrisCore.getInstance(), "doublejumpboots");
        ShapedRecipe recipe = new ShapedRecipe(key, hoe);

        recipe.shape("FFF", "FBF", "FFF");

        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('B', Material.IRON_BOOTS);

        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(IrisCore.getInstance().getPlayerManager().getTribe(p) != 5 && e.getRecipe().getResult().getItemMeta().getDisplayName().contains("Double Jumpers")) {
            p.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Das können nur §e" + IrisCore.getInstance().getTribeManager().getTribeColor(5) + IrisCore.getInstance().getTribeManager().getTribeName(5) + " §7herstellen");
            p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1, 1);
            p.closeInventory();
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onGmChange(PlayerGameModeChangeEvent e) {
        e.getPlayer().setAllowFlight(true);
    }

}
