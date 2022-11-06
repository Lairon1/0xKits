package com.lairon.plugin.xkits.kit;

import lombok.Builder;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
@Builder
public class KitItemsHolder implements Cloneable {

    // Armor
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    private ItemStack offHand;

    private ItemStack[] inventory;

    @Override
    protected KitItemsHolder clone() {
        ItemStack[] cloneInventory = new ItemStack[inventory.length];

        for (int i = 0; i < cloneInventory.length; i++) {
            cloneInventory[i] = inventory[i] != null ? inventory[i].clone() : null;
        }

        return KitItemsHolder
                .builder()
                .helmet(helmet.clone())
                .chestplate(chestplate.clone())
                .leggings(leggings.clone())
                .boots(boots.clone())
                .offHand(offHand.clone())
                .inventory(cloneInventory)
                .build();
    }
}
