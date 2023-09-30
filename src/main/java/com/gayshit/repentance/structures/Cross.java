package com.gayshit.repentance.structures;

import com.gayshit.repentance.util.NPC;
import com.gayshit.repentance.util.StructureBuilder;
import com.gayshit.repentance.util.enums.NPCSkin;
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
    private static Map<Vector, Material> getStructureMap(int variant) {
        Map<Vector, Material> structureMap = new HashMap<>();

        // shaft
        for (int y = 0; y < 10; y++) {
            addBlockToStructure(structureMap, variant, 0, y, 0, Material.OAK_LOG);
        }

        // another shaft ig?
        for (int horizontal1 = -2; horizontal1 <= 2; horizontal1++) {
            addBlockToStructure(structureMap, variant, horizontal1, 7, 0, Material.OAK_WOOD);
        }

        // dirt
        for (int horizontal1 = -1; horizontal1  <= 1; horizontal1++) {
            for (int horizontal2 = -1; horizontal2  <= 1; horizontal2++) {
                addBlockToStructure(structureMap, variant, horizontal1, 0, horizontal2, Material.ROOTED_DIRT);
            }
        }
        addBlockToStructure(structureMap, variant, 2, 0, 0, Material.ROOTED_DIRT);
        addBlockToStructure(structureMap, variant, -2, 0, 0, Material.ROOTED_DIRT);
        addBlockToStructure(structureMap, variant, 0, 0, 2, Material.ROOTED_DIRT);
        addBlockToStructure(structureMap, variant, 0, 0, -2, Material.ROOTED_DIRT);

        addBlockToStructure(structureMap, variant, 1, 1, 0, Material.ROOTED_DIRT);
        addBlockToStructure(structureMap, variant, -1, 1, 0, Material.ROOTED_DIRT);
        addBlockToStructure(structureMap, variant, 0, 1, 1, Material.ROOTED_DIRT);
        addBlockToStructure(structureMap, variant, 0, 1, -1, Material.ROOTED_DIRT);

        addBlockToStructure(structureMap, variant, 1, 2, 0, Material.IRON_BARS);
        addBlockToStructure(structureMap, variant, -1, 1, 1, Material.IRON_BARS);

        addBlockToStructure(structureMap, variant, -1, 2, 0, Material.DEAD_BUSH);
        addBlockToStructure(structureMap, variant, 2, 1, 0, Material.DEAD_BUSH);
        addBlockToStructure(structureMap, variant, 0, 1, 2, Material.DEAD_BUSH);

        addBlockToStructure(structureMap, variant, 0, 7, 1, Material.LIGHT);


        return structureMap;
    }

    private static void addBlockToStructure(
            Map<Vector, Material> structureMap,
            int variant,
            int horizontal,
            int y,
            int depth,
            Material material
    ) {
        switch (variant) {
            case 0 -> structureMap.put(new Vector(-horizontal, y, -depth), material);
            case 1 -> structureMap.put(new Vector(depth, y, horizontal), material);
            case 2 -> structureMap.put(new Vector(horizontal, y, depth), material);
            case 3 -> structureMap.put(new Vector(-depth, y, -horizontal), material);
        }
    }

    public static void build(Player player, int startX, int startY, int startZ, int variant) {
        playMusic(player);

        StructureBuilder.build(player, startX, startY, startZ, getStructureMap(variant));

        switch (variant) {
            case 0 -> NPC.spawnNpc(player, startX + 0.5, startY + 6.5, startZ - 0.1, 180, NPCSkin.JESUS);
            case 1 -> NPC.spawnNpc(player, startX + 1.1, startY + 6.5, startZ + 0.5, 270, NPCSkin.JESUS);
            case 2 -> NPC.spawnNpc(player, startX + 0.5, startY + 6.5, startZ + 1.1, 0, NPCSkin.JESUS);
            case 3 -> NPC.spawnNpc(player, startX - 0.1, startY + 6.5, startZ + 0.5, 90, NPCSkin.JESUS);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                switch (variant) {
                    case 0 -> spawnNiggas(player, startX, startY, startZ, 1, -1, -1, -1, 180);
                    case 1 -> spawnNiggas(player, startX, startY, startZ, 1, 1, 1, -1, 270);
                    case 2 -> spawnNiggas(player, startX, startY, startZ, -1, 1, 1, 1, 360);
                    case 3 -> spawnNiggas(player, startX, startY, startZ, -1, -1, -1, 1, 90);
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
