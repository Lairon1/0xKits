package com.lairon.plugin.xkits.kit;

import lombok.Builder;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
@Builder
public class KitItemsHolder {

    // Armor
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    private ItemStack offHand;

    private ItemStack[] inventory;

}
