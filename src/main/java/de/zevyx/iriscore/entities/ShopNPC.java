package de.zevyx.iriscore.entities;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.UUID;

public class ShopNPC {

    public static void spawnBackpackShop(Player player) {
        Villager villager = player.getWorld().spawn(player.getLocation(), Villager.class);
        villager.setCustomName("§8» §eBackpack Shop");
        villager.setProfession(Villager.Profession.LEATHERWORKER);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.setSilent(true);
        villager.setGravity(false);
    }


}
