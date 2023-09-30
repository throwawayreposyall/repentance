package com.gayshit.repentance.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Bible {
    public static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("Holy Bible");
        itemStack.setItemMeta(meta);
        return  itemStack;
    }
}
