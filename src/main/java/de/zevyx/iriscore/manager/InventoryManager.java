package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.api.InventoryAnimationAPI;
import de.zevyx.iriscore.api.ItemBuilderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class InventoryManager {

    public void openStatsInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§8Stats");
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);


        new InventoryAnimationAPI(inv, p, fill).addItem(10, new ItemStack(Material.SKELETON_SKULL)).addItem(48, new ItemStack(Material.STONE_BUTTON)).addItem(49, new ItemStack(Material.BARRIER)).addItem(50, new ItemStack(Material.STONE_BUTTON))
                .addItem(11, new ItemStack(Material.SKELETON_SKULL)).addItem(12, new ItemStack(Material.SKELETON_SKULL)).addItem(13, new ItemStack(Material.SKELETON_SKULL)).addItem(14, new ItemStack(Material.SKELETON_SKULL)).addItem(15, new ItemStack(Material.SKELETON_SKULL)).addItem(16, new ItemStack(Material.SKELETON_SKULL))
                .addItem(19, new ItemStack(Material.SKELETON_SKULL)).addItem(20, new ItemStack(Material.SKELETON_SKULL)).addItem(21, new ItemStack(Material.SKELETON_SKULL)).addItem(22, new ItemStack(Material.SKELETON_SKULL)).addItem(23, new ItemStack(Material.SKELETON_SKULL)).addItem(24, new ItemStack(Material.SKELETON_SKULL)).addItem(25, new ItemStack(Material.SKELETON_SKULL))
                .addItem(28, new ItemStack(Material.SKELETON_SKULL)).addItem(29, new ItemStack(Material.SKELETON_SKULL)).addItem(30, new ItemStack(Material.SKELETON_SKULL)).addItem(31, new ItemStack(Material.SKELETON_SKULL)).addItem(32, new ItemStack(Material.SKELETON_SKULL)).addItem(33, new ItemStack(Material.SKELETON_SKULL)).addItem(34, new ItemStack(Material.SKELETON_SKULL))
                .addItem(37, new ItemStack(Material.SKELETON_SKULL)).addItem(38, new ItemStack(Material.SKELETON_SKULL)).addItem(39, new ItemStack(Material.SKELETON_SKULL)).addItem(40, new ItemStack(Material.SKELETON_SKULL)).addItem(41, new ItemStack(Material.SKELETON_SKULL)).addItem(42, new ItemStack(Material.SKELETON_SKULL)).addItem(43, new ItemStack(Material.SKELETON_SKULL))
                .animate();
   }

   public void openTribeInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 5, "§8Tribes");
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        new InventoryAnimationAPI(inv, p, fill)
                .addItem(10, new ItemBuilderAPI(Material.WOODEN_HOE).setName("§cNemokamar").build())
                .addItem(12, new ItemBuilderAPI(Material.WOODEN_AXE).setName("§aMedikirtis").build())
                .addItem(14, new ItemBuilderAPI(Material.WHEAT).setName("§eFermer").build())
                .addItem(16, new ItemBuilderAPI(Material.IRON_PICKAXE).setName("§8Kalnas").build())
                .addItem(28, new ItemBuilderAPI(Material.STONE_SWORD).setName("§dAkarier").build())
                .addItem(30, new ItemBuilderAPI(Material.FISHING_ROD).setName("§bEceras").build())
                .addItem(32, new ItemBuilderAPI(Material.POTION).setName("§6Maqas").build())
                .addItem(34, new ItemBuilderAPI(Material.TOTEM_OF_UNDYING).setName("§5Bedvasis Mirtis").build())
                .animate();
   }

    public void openSingleTribeInventory(Player p, Integer tribe) {
        Inventory inv = Bukkit.createInventory(null, 9 * 6, IrisCore.getInstance().getTribeManager().getTribeColor(tribe) + " " + IrisCore.getInstance().getTribeManager().getTribeName(tribe));
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        ArrayList<String> players = IrisCore.getInstance().getTribeManager().getTribeMembers(tribe);

        new InventoryAnimationAPI(inv, p, fill)
                .addItem(10, players.size() > 0 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(0))).getName()).setSkullOwner(players.get(0)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(0)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(0)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(0)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(12, players.size() > 1 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(1))).getName()).setSkullOwner(players.get(1)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(1)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(1)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(1)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(14, players.size() > 2 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(2))).getName()).setSkullOwner(players.get(2)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(2)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(2)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(2)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(16, players.size() > 3 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(3))).getName()).setSkullOwner(players.get(3)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(3)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(3)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(3)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(19, players.size() > 4 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(4))).getName()).setSkullOwner(players.get(4)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(4)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(4)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(4)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(21, players.size() > 5 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(5))).getName()).setSkullOwner(players.get(5)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(5)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(5)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(5)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(23, players.size() > 6 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(6))).getName()).setSkullOwner(players.get(6)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(6)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(6)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(6)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(25, players.size() > 7 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(7))).getName()).setSkullOwner(players.get(7)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(7)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(7)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(7)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(28, players.size() > 8 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(8))).getName()).setSkullOwner(players.get(8)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(8)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(8)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(8)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(30, players.size() > 9 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(9))).getName()).setSkullOwner(players.get(9)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(9)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(9)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(9)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(32, players.size() > 10 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(10))).getName()).setSkullOwner(players.get(10)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(10)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(10)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(10)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(34, players.size() > 11 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(11))).getName()).setSkullOwner(players.get(11)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(11)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(11)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(11)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(37, players.size() > 12 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(12))).getName()).setSkullOwner(players.get(12)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(12)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(12)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(12)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(39, players.size() > 13 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(13))).getName()).setSkullOwner(players.get(13)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(13)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(13)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(13)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(41, players.size() > 14 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(14))).getName()).setSkullOwner(players.get(14)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(14)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(14)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(14)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .addItem(43, players.size() > 15 ? new ItemBuilderAPI(Material.PLAYER_HEAD).setName("§8» §6" + Bukkit.getOfflinePlayer(UUID.fromString(players.get(15))).getName()).setSkullOwner(players.get(15)).setLore(Arrays.asList(" ", "§6Status §8» " + getOnlineStatus(players.get(15)), "§6Kills §8» §e" + IrisCore.getInstance().getPlayerManager().getKills(players.get(15)), "§6Deaths §8» §e" + IrisCore.getInstance().getPlayerManager().getDeaths(players.get(15)))).build() : new ItemStack(Material.SKELETON_SKULL))
                .animate();
    }

    private String getOnlineStatus(String uuid) {
        return Bukkit.getOfflinePlayer(UUID.fromString(uuid)).isOnline() ? "§aOnline" : "§cOffline";
    }

   public void openAdminInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, "§8Admin");
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        new InventoryAnimationAPI(inv, p, fill)
                .addItem(10, new ItemBuilderAPI(Material.CAMPFIRE).setName("§6Tribes").build())
                .animate();
   }

   public void test(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, "TEST");
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        new InventoryAnimationAPI(inv, p, fill)
                .addItem(10, new ItemBuilderAPI(Material.CAMPFIRE).setName("§6Tribes").build())
                .animate();
   }
    public void openBackpackShop(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Backpacks");
        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        ItemStack upgrade = new ItemBuilderAPI(Material.BARRIER).setName("§8» §cKein Update verfügbar").build();

        Integer level = IrisCore.getInstance().getPlayerManager().getBackpackLevel(p.getUniqueId().toString());
        Bukkit.broadcastMessage(level.toString());

        if(level == 1)
            upgrade = new ItemBuilderAPI(Material.LEATHER).setName("§8» §aUpgrade zu Level 2").build();
        else if(level == 2)
            upgrade = new ItemBuilderAPI(Material.IRON_INGOT).setName("§8» §aUpgrade zu Level 3").build();
        else if(level == 3)
            upgrade = new ItemBuilderAPI(Material.GOLD_INGOT).setName("§8» §aUpgrade zu Level 4").build();
        else if(level == 4)
            upgrade = new ItemBuilderAPI(Material.DIAMOND).setName("§8» §aUpgrade zu Level 5").build();
        else if(level == 5)
            upgrade = new ItemBuilderAPI(Material.EMERALD).setName("§8» §aUpgrade zu Level 6").build();

        new InventoryAnimationAPI(inv, p, fill)
                .addItem(13, upgrade)
                .animate();

    }

}
