package de.zevyx.iriscore.entities;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.zevyx.iriscore.IrisCore;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardTeam;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.EntityPose;
import net.minecraft.world.scores.ScoreboardTeam;
import net.minecraft.world.scores.ScoreboardTeamBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.scoreboard.CraftScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CorpseEntity {

    public static void create(Player player) {
        EntityPlayer craftPlayer = ((CraftPlayer) player).getHandle();

        Property textures = (Property) craftPlayer.getBukkitEntity().getProfile().getProperties().get("textures").toArray()[0];
        GameProfile profile = new GameProfile(UUID.randomUUID(), player.getName());
        profile.getProperties().put("textures", new Property("textures", textures.getValue(), textures.getSignature()));

        EntityPlayer corpse = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) player.getWorld()).getHandle(), profile, null);
        corpse.getBukkitEntity().teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));

        Location bed = player.getLocation().add(1, 0, 0);
        corpse.e(new BlockPosition(bed.getX(), bed.getY(), bed.getZ()));

        ScoreboardTeam team = new ScoreboardTeam(
                ((CraftScoreboard) Bukkit.getScoreboardManager().getMainScoreboard()).getHandle(), player.getName());
        team.a(ScoreboardTeamBase.EnumNameTagVisibility.b);

        PacketPlayOutScoreboardTeam score1 = PacketPlayOutScoreboardTeam.a(team);
        PacketPlayOutScoreboardTeam score2 = PacketPlayOutScoreboardTeam.a(team, true);
        PacketPlayOutScoreboardTeam score3 = PacketPlayOutScoreboardTeam.a(team, corpse.getBukkitEntity().getName(), PacketPlayOutScoreboardTeam.a.a);

        corpse.g(EntityPose.c);

//        DataWatcher watcher = corpse.ai();
//        byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
//        watcher.a(DataWatcherRegistry.a.a(17), b); // wiki.vg -> Player

        PacketPlayOutEntity.PacketPlayOutRelEntityMove move = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(corpse.getBukkitEntity().getEntityId(), (byte) 0, (byte) ((player.getLocation().getY() - 1.7 - player.getLocation().getY()) * 32), (byte) 0, false);

        for (Player all : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) all).getHandle().b;
            connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, corpse));
            connection.a(new PacketPlayOutNamedEntitySpawn(corpse));

            connection.a(score1);
            connection.a(score2);
            connection.a(score3);

            // connection.a(new PacketPlayOutEntityMetadata(corpse.ar(), watcher, true));
            connection.a(move);

            new BukkitRunnable() {
                @Override
                public void run() {
                    connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, corpse));
                }
            }.runTaskAsynchronously(IrisCore.getInstance());
        }
    }

}
