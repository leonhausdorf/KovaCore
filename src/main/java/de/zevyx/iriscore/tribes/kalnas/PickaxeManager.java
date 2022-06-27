package de.zevyx.iriscore.tribes.kalnas;

import de.zevyx.iriscore.api.ItemBuilderAPI;
import de.zevyx.iriscore.utils.CustomEnchant;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class PickaxeManager {

    public void autosmeltOre(Player p, Location location) {
        if(!p.getInventory().getItemInMainHand().hasItemMeta()) return;
        if(!p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchant.AUTOSMELT)) return;

        if(location.getBlock().getType() == Material.IRON_ORE) {
            if(p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                location.getBlock().setType(Material.AIR);
                location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
                p.getInventory().getItemInMainHand().setDurability((short) (p.getInventory().getItemInMainHand().getDurability() + 1));
                for(int i = 0; i < getFortuneOne(); i++) {
                    location.getWorld().dropItemNaturally(location, new ItemBuilderAPI(Material.IRON_INGOT).setAmount(1).build());
                }
            }
            if(p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                location.getBlock().setType(Material.AIR);
                location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
                p.getInventory().getItemInMainHand().setDurability((short) (p.getInventory().getItemInMainHand().getDurability() + 1));
                for(int i = 0; i < getFortuneTwo(); i++) {
                    location.getWorld().dropItemNaturally(location, new ItemBuilderAPI(Material.IRON_INGOT).setAmount(1).build());
                }
            }
            if(p.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                location.getBlock().setType(Material.AIR);
                location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 0);
                p.getInventory().getItemInMainHand().setDurability((short) (p.getInventory().getItemInMainHand().getDurability() + 1));
                for(int i = 0; i < getFortuneThree(); i++) {
                    location.getWorld().dropItemNaturally(location, new ItemBuilderAPI(Material.IRON_INGOT).setAmount(1).build());
                }
            }
        }


    }

    public Integer getFortuneOne() {
        int random =  (int)(Math.random() * 100) + 1;
        return random < 30  ? 2 : 1;
    }

    public Integer getFortuneTwo() {
        int random =  (int)(Math.random() * 100) + 1;
        if(random < 20) {
            return 3;
        } else if(random > 20 && random <= 50) {
            return 2;
        } else {
            return 1;
        }
    }

    public Integer getFortuneThree() {
        int random =  (int)(Math.random() * 100) + 1;
        if(random < 20) {
            return 3;
        } else if(random > 20 && random <= 50) {
            return 2;
        } else {
            return 1;
        }
    }

}
