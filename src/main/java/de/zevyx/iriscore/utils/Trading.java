package de.zevyx.iriscore.utils;

import de.zevyx.iriscore.api.InventoryBuilder;
import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.api.ItemBuilderAPI;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Trading {

    public Trading(Player player, Player target) {
        this.player = player;
        this.target = target;
        playerInventory = IrisAPI.getInstance().getInventoryBuilder().name("§6§lHandel §7mit §e" + target.getName())
                .build();
        targetInventory = IrisAPI.getInstance().getInventoryBuilder().name("§6§lHandel §7mit §e" + player.getName())
                .line(0, InventoryBuilder.getClearMaterial(Material.GRAY_STAINED_GLASS_PANE))
                .line(4, InventoryBuilder.getClearMaterial(Material.GRAY_STAINED_GLASS_PANE))
                .verticalLine(0, InventoryBuilder.getClearMaterial(Material.GRAY_STAINED_GLASS_PANE))
                .verticalLine(8, InventoryBuilder.getClearMaterial(Material.GRAY_STAINED_GLASS_PANE))
                .verticalLine(4, InventoryBuilder.getClearMaterial(Material.IRON_BARS))
                .item(18, new ItemBuilderAPI(Material.PLAYER_HEAD).setSkullOwner(target).build())
                .item(26, new ItemBuilderAPI(Material.PLAYER_HEAD).setSkullOwner(player).build())
                .item(26, InventoryBuilder.getClearMaterial(Material.GRAY_STAINED_GLASS_PANE))
                .build();
    }

    @Getter
    private final Player player;
    @Getter
    private final Player target;
    @Getter
    private final Inventory playerInventory;
    @Getter
    private final Inventory targetInventory;


}
