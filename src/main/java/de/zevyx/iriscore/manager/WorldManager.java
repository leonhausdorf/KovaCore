package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class WorldManager {

    public void loadAllWorlds() {
        IrisCore.getInstance().getWorldConfig().getWorlds().forEach(world -> {
            Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Versucht die Welt §e" + world + " §8zu laden...");
            new WorldCreator(world).createWorld();
            Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Welt §e" + world + " §7wurde erfolgreich geladen!");
        });
    }

    public boolean addNewWorld(String world) {
        IrisCore.getInstance().getWorldConfig().addWorld(world);
        IrisCore.getInstance().getWorldConfig().save();
        Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Versuche die Welt §e" + world + " §8zu laden...");
        if (new WorldCreator(world).createWorld() != null) {
            Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Welt §e" + world + " §7wurde erfolgreich geladen!");
            return true;
        } else {
            Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Welt §e" + world + " §7konnte nicht geladen werden!");
            return false;
        }
    }

    public void removeWorld(String world) {

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.getLocation().getWorld().getName().equalsIgnoreCase(world)) {
                all.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                all.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Du wurdest in die Standart Welt teleportiert, da die andere Welt gelöscht wurde!");
            }
        }

        IrisCore.getInstance().getWorldConfig().removeWorld(world);
        IrisCore.getInstance().getWorldConfig().save();
        Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Versuche die Welt §e" + world + " §8zu löschen...");
        Bukkit.unloadWorld(world, false);
        Bukkit.getConsoleSender().sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Welt §e" + world + " §7wurde erfolgreich gelöscht!");
    }

}
