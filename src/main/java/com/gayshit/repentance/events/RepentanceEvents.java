package com.gayshit.repentance.events;

import com.gayshit.repentance.items.Bible;
import com.gayshit.repentance.structures.Cross;
import com.gayshit.repentance.util.enums.StructureDirection;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class RepentanceEvents implements Listener {
    private final JavaPlugin plugin;
    Map<Player, Boolean> isPraying = new HashMap<>();
    Map<Player, BukkitRunnable> activePrayerTasks = new HashMap<>();


    public RepentanceEvents(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player eventPlayer = event.getPlayer();
        Location playerLocation = eventPlayer.getLocation();

        if (!(eventPlayer.isSneaking() &&
                playerLocation.getPitch() > 40 &&
                eventPlayer.getInventory().getItemInMainHand().isSimilar(Bible.getItem()))) {
            isPraying.put(eventPlayer, false);
            if (activePrayerTasks.containsKey(eventPlayer)) {
                activePrayerTasks.get(eventPlayer).cancel();
            }
            return;
        }

        if (isPraying.containsKey(eventPlayer) && isPraying.get(eventPlayer)) return;

        eventPlayer.setPlayerWeather(WeatherType.DOWNFALL);
        BukkitRunnable buildCrossTask = new BukkitRunnable() {
            @Override
            public void run() {
                buildCross(playerLocation, eventPlayer);
                isPraying.put(eventPlayer, false);
            }
        };
        buildCrossTask.runTaskLater(plugin, 60);
        activePrayerTasks.put(eventPlayer, buildCrossTask);
        isPraying.put(eventPlayer, true);
    }

    private void buildCross(Location playerLocation, Player player) {
        World world = playerLocation.getWorld();

        double distance = 8;
        double playerYaw = playerLocation.getYaw();
        double playerYawDegrees = Math.toRadians(playerYaw);

        int startX = (int) (playerLocation.getX() - distance * Math.sin(playerYawDegrees));
        int startY = (int) playerLocation.getY();
        int startZ = (int) (playerLocation.getZ() + distance * Math.cos(playerYawDegrees));

        world.strikeLightningEffect(new Location(world, startX, startY, startZ));

        new BukkitRunnable() {
            @Override
            public void run() {
                world.strikeLightningEffect(new Location(world, startX, startY, startZ));
            }
        }.runTaskLater(plugin, 20);

        if (-45 <= playerYaw && playerYaw <= 45) {
            Cross.build(player, startX, startY, startZ, StructureDirection.SOUTH);
        } else if (45 <= playerYaw && playerYaw <= 135) {
            Cross.build(player, startX, startY, startZ, StructureDirection.WEST);
        } else if (135 <= playerYaw || playerYaw <= -135) {
            Cross.build(player, startX, startY, startZ, StructureDirection.NORTH);
        } else  {
            Cross.build(player, startX, startY, startZ, StructureDirection.EAST);
        }
    }
}
