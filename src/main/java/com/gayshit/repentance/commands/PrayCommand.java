package com.gayshit.repentance.commands;

import com.gayshit.repentance.items.Bible;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).getInventory().addItem(Bible.getItem());
        }
        return false;
    }
}
