package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.utils.CooldownType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    private final HashMap<UUID, HashMap<CooldownType, Long>> cooldowns = new HashMap<>();

    public CooldownManager() {
        start();
    }

    public void start() {
        new BukkitRunnable() {
            public void run() {
                for (UUID uuid : cooldowns.keySet()) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player == null) {
                        cooldowns.remove(uuid);
                        continue;
                    }
                    HashMap<CooldownType, Long> cooldowns = CooldownManager.this.cooldowns.get(uuid);

                    if (cooldowns.isEmpty()) {
                        continue;
                    }

                    String message = "";
                    for (CooldownType cooldownType : cooldowns.keySet()) {
                        if (!cooldownType.isShown()) {
                            continue;
                        }
                        double endTime = cooldowns.get(cooldownType); // 120
                        double startTime = endTime - (cooldownType.getCooldown() * 1000L); // 20
                        double timeNeed = endTime - startTime;
                        double timePassed = System.currentTimeMillis() - startTime;

                        double time = (timePassed * 100L) / timeNeed; // 30 * 120 / 80 =

                        int symbols = 40 / cooldowns.size();
                        int colorize = (int) (symbols * Math.round(time) / 100);
                        if (colorize > symbols) {
                            colorize = symbols;
                        }
                        String greenColored = "";
                        for (int i = 0; i < colorize; i++) {
                            greenColored += "¦";
                        }
                        String redColored = "";
                        for (int i = 0; i < symbols - colorize; i++) {
                            redColored += "¦";
                        }

                        message += " §7" + cooldownType.getName() + "§8: [§a" + greenColored + "§c" + redColored + "§8] §0|";
                    }

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message.substring(0, message.length() - 4)));

                    for (CooldownType type : cooldowns.keySet()) {
                        Long timeOut = cooldowns.get(type);
                        if (timeOut <= System.currentTimeMillis()) {
                            cooldowns.remove(type);
                            if (type.getFinishSound() != null) {
                                player.playSound(player.getLocation(), type.getFinishSound(), 10, 1);
                            }
                            if(type == CooldownType.TELEPORTATION) {
                                if(IrisCore.getInstance().getPortalManager().hasPortalDestinationLocation(IrisCore.getInstance().getPortalManager().getNearestPortalName(player.getLocation()))) {
                                    player.teleport(IrisCore.getInstance().getPortalManager().getPortalDestinationLocation(IrisCore.getInstance().getPortalManager().getNearestPortalName(player.getLocation())));
                                } else {
                                    player.sendMessage(IrisCore.getInstance().getMessageConfig().getPrefix() + "Dieses Portal führt ins nichts. Es wäre nicht klug hindurch zu gehen.");
                                }
                            }
                            continue;
                        }
                    }
                    //TODO: Actionbar
                }
            }
        }.runTaskTimer(IrisCore.getInstance(), 20, 3);
    }

    public void addCooldown(UUID uuid, CooldownType cooldownType) {
        HashMap<CooldownType, Long> playerCooldowns = cooldowns.getOrDefault(uuid, new HashMap<CooldownType, Long>());
        if (playerCooldowns.containsKey(cooldownType)) {
            playerCooldowns.replace(cooldownType, System.currentTimeMillis() + (cooldownType.getCooldown() * 1000L));
        } else {
            playerCooldowns.put(cooldownType, System.currentTimeMillis() + (cooldownType.getCooldown() * 1000L));
        }
        if (cooldowns.containsKey(uuid)) {
            cooldowns.replace(uuid, playerCooldowns);
        } else {
            cooldowns.put(uuid, playerCooldowns);
        }
    }

    public void removeCooldown(UUID uuid, CooldownType cooldownType) {
        HashMap<CooldownType, Long> playerCooldowns = cooldowns.getOrDefault(uuid, new HashMap<CooldownType, Long>());
        if (playerCooldowns.containsKey(cooldownType)) {
            playerCooldowns.remove(cooldownType);
        }
        if (cooldowns.containsKey(uuid)) {
            cooldowns.replace(uuid, playerCooldowns);
        } else {
            cooldowns.put(uuid, playerCooldowns);
        }
    }


    public boolean hasCooldown(UUID uuid, CooldownType cooldownType) {
        if (!cooldowns.containsKey(uuid)) {
            return false;
        }
        HashMap<CooldownType, Long> cooldowns = this.cooldowns.get(uuid);
        boolean active = false;
        for (CooldownType type : cooldowns.keySet()) {
            Long timeOut = cooldowns.get(type);
            if (timeOut >= System.currentTimeMillis()) {
                if(type == cooldownType) {
                    active = true;
                }
            }
        }
        return active;
    }
}
