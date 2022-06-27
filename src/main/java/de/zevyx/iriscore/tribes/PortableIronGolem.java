package de.zevyx.iriscore.tribes;

import de.zevyx.iriscore.api.ItemBuilderAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PortableIronGolem {
    public static ItemStack getItemFromIronGolem(IronGolem ironGolem) {
        return new ItemBuilderAPI(Material.GHAST_SPAWN_EGG)
                .setName("§rPortable Iron Golem")
                .setLore(" ", "§8HP: " + ironGolem.getHealth() + "/" + ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue())
                .build();
    }

    public static IronGolem spawnIrongolemAtPositionByItem(Location location, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.getDisplayName().equals("§rPortable Iron Golem") || itemMeta.getLore().isEmpty()) {
            return null;
        }
        String loreLine = itemMeta.getLore().get(1);
        String[] healths = loreLine.replaceAll("§8HP: ", "").split("/");
        int health = Integer.parseInt(healths[0]);
        int maxHeath = Integer.parseInt(healths[1]);

        IronGolem ironGolem = (IronGolem) location.getWorld().spawnEntity(location, EntityType.IRON_GOLEM);
        ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHeath);
        ironGolem.setHealth(health);
        return ironGolem;
    }
}
