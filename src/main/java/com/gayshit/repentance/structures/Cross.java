package com.gayshit.repentance.structures;

import com.gayshit.repentance.util.NPC;
import com.gayshit.repentance.util.StructureBuilder;
import com.gayshit.repentance.util.enums.NPCSkin;
import com.gayshit.repentance.util.enums.StructureDirection;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.EntitySongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Cross {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin plugin) {
        Cross.plugin = plugin;
    }
    private static Map<Vector, Material> getStructureMap(StructureDirection structureDirection) {
        Map<Vector, Material> structureMap = new HashMap<>();

        // shaft
        for (int y = 0; y < 10; y++) {
            addBlockToStructure(structureMap, 0, y, 0, Material.OAK_LOG, structureDirection);
        }

        // another shaft ig?
        for (int horizontal1 = -2; horizontal1 <= 2; horizontal1++) {
            addBlockToStructure(structureMap, horizontal1, 7, 0, Material.OAK_WOOD, structureDirection);
        }

        // dirt
        for (int horizontal1 = -1; horizontal1  <= 1; horizontal1++) {
            for (int horizontal2 = -1; horizontal2  <= 1; horizontal2++) {
                addBlockToStructure(structureMap, horizontal1, 0, horizontal2, Material.ROOTED_DIRT, structureDirection);
            }
        }
        addBlockToStructure(structureMap, 2, 0, 0, Material.ROOTED_DIRT, structureDirection);
        addBlockToStructure(structureMap, -2, 0, 0, Material.ROOTED_DIRT, structureDirection);
        addBlockToStructure(structureMap, 0, 0, 2, Material.ROOTED_DIRT, structureDirection);
        addBlockToStructure(structureMap, 0, 0, -2, Material.ROOTED_DIRT, structureDirection);

        addBlockToStructure(structureMap, 1, 1, 0, Material.ROOTED_DIRT, structureDirection);
        addBlockToStructure(structureMap, -1, 1, 0, Material.ROOTED_DIRT, structureDirection);
        addBlockToStructure(structureMap, 0, 1, 1, Material.ROOTED_DIRT, structureDirection);
        addBlockToStructure(structureMap, 0, 1, -1, Material.ROOTED_DIRT, structureDirection);

        addBlockToStructure(structureMap, 1, 2, 0, Material.IRON_BARS, structureDirection);
        addBlockToStructure(structureMap, -1, 1, 1, Material.IRON_BARS, structureDirection);

        addBlockToStructure(structureMap, -1, 2, 0, Material.DEAD_BUSH, structureDirection);
        addBlockToStructure(structureMap, 2, 1, 0, Material.DEAD_BUSH, structureDirection);
        addBlockToStructure(structureMap, 0, 1, 2, Material.DEAD_BUSH, structureDirection);

        addBlockToStructure(structureMap, 0, 7, 1, Material.LIGHT, structureDirection);


        return structureMap;
    }

    private static void addBlockToStructure(
            Map<Vector, Material> structureMap,
            int horizontal,
            int y,
            int depth,
            Material material,
            StructureDirection structureDirection
    ) {
        switch (structureDirection) {
            case SOUTH -> structureMap.put(new Vector(-horizontal, y, -depth), material);
            case WEST -> structureMap.put(new Vector(depth, y, horizontal), material);
            case NORTH -> structureMap.put(new Vector(horizontal, y, depth), material);
            case EAST -> structureMap.put(new Vector(-depth, y, -horizontal), material);
        }
    }

    public static void build(Player player, int startX, int startY, int startZ, StructureDirection structureDirection) {
        playMusic(player);

        StructureBuilder.build(player, startX, startY, startZ, getStructureMap(structureDirection));

        switch (structureDirection) {
            case SOUTH -> NPC.spawnNpc(player, startX + 0.5, startY + 6.5, startZ - 0.1, 180, NPCSkin.JESUS);
            case WEST -> NPC.spawnNpc(player, startX + 1.1, startY + 6.5, startZ + 0.5, 270, NPCSkin.JESUS);
            case NORTH -> NPC.spawnNpc(player, startX + 0.5, startY + 6.5, startZ + 1.1, 0, NPCSkin.JESUS);
            case EAST -> NPC.spawnNpc(player, startX - 0.1, startY + 6.5, startZ + 0.5, 90, NPCSkin.JESUS);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                switch (structureDirection) {
                    case SOUTH -> spawnNiggas(player, startX, startY, startZ, 1, -1, -1, -1, 180);
                    case WEST -> spawnNiggas(player, startX, startY, startZ, 1, 1, 1, -1, 270);
                    case NORTH -> spawnNiggas(player, startX, startY, startZ, -1, 1, 1, 1, 360);
                    case EAST -> spawnNiggas(player, startX, startY, startZ, -1, -1, -1, 1, 90);
                }
            }
        }.runTaskLater(plugin, 180);
    }

    private static void playMusic(Player player) {
        File nbsFile = new File(plugin.getDataFolder(), "repentance.nbs");

        if (!nbsFile.exists()) {
            player.sendMessage("Add repentance.nbs to the data folder");
            return;
        }

        Song song = NBSDecoder.parse(nbsFile);
        EntitySongPlayer esp = new EntitySongPlayer(song);
        esp.setEntity(player);
        esp.setDistance(16); // Default: 16
        esp.addPlayer(player);
        esp.setPlaying(true);
    }

    private static void spawnNiggas(
            Player player,
            int startX,
            int startY,
            int startZ,
            int xMultiplier1,
            int xMultiplier2,
            int zMultiplier1,
            int zMultiplier2,
            int yawOffset
    ) {
        player.setPlayerWeather(WeatherType.DOWNFALL);

        Map<ServerPlayer, Integer> thugList = new HashMap<>();

        thugList.put(
            NPC.spawnNpc(
                    player,
                    startX + 2 * xMultiplier1,
                    startY,
                    startZ + 2 * zMultiplier1,
                    -15 + yawOffset,
                    NPCSkin.NIGGA
            ),
                -15 + yawOffset
        );
        thugList.put(
            NPC.spawnNpc(
                    player,
                    startX + 3 * xMultiplier1,
                    startY,
                    startZ + 3 * zMultiplier1,
                    -45 + yawOffset,
                    NPCSkin.NIGGA
            ),
                -45 + yawOffset
        );
        thugList.put(
            NPC.spawnNpc(
                    player,
                    startX + 2 * xMultiplier2,
                    startY,
                    startZ + 2 * zMultiplier2,
                    15 + yawOffset,
                    NPCSkin.NIGGA
            ),
                15 + yawOffset
        );
        thugList.put(
            NPC.spawnNpc(
                    player,
                    startX + 3 * xMultiplier2,
                    startY,
                    startZ + 3 * zMultiplier2,
                    45 + yawOffset,
                    NPCSkin.NIGGA
            ),
                45 + yawOffset
        );

        for (Map.Entry<ServerPlayer, Integer> thugYawEntry : thugList.entrySet()) {
            ServerPlayer thug = thugYawEntry.getKey();
            int yaw = thugYawEntry.getValue();

            NPC.spawnPenis(player, thug.getX(), thug.getY(), thug.getZ(), yaw);
        }
    }
}
