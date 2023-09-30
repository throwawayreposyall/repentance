package com.gayshit.repentance.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Map;

public class StructureBuilder {
    public static void build(Player player, int startX, int startY, int startZ, Map<Vector, Material> structureMap) {
        for (Map.Entry<Vector, Material> blockEntry : structureMap.entrySet()) {
            Vector placement = blockEntry.getKey();
            Material material = blockEntry.getValue();

            int x = startX + placement.getBlockX();
            int y = startY + placement.getBlockY();
            int z = startZ + placement.getBlockZ();

            player.sendBlockChange(new Location(player.getWorld(), x, y, z), material.createBlockData());
        }
    }
}
