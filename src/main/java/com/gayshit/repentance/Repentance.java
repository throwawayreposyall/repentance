package com.gayshit.repentance;

import com.gayshit.repentance.commands.PrayCommand;
import com.gayshit.repentance.events.RepentanceEvents;
import com.gayshit.repentance.structures.Cross;
import com.gayshit.repentance.util.NPC;
import org.bukkit.plugin.java.JavaPlugin;

public final class Repentance extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            this.getDataFolder().mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cross.init(this);

        getCommand("pray").setExecutor(new PrayCommand());

        getServer().getPluginManager().registerEvents(new RepentanceEvents(this), this);
    }
}
