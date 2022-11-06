package com.lairon.plugin.xkits.kit;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
@Builder
public class Kit implements Cloneable {

    @NonNull
    private String id;

    @NonNull
    private String name;

    /**
     * How long does a player have to wait to take a kit again
     * if the value is 0 then there will be no delay
     * (In seconds)
     */
    @NonNull
    private int cooldown;

    /**
     * {@link KitGUIData} used to store information that
     * will be displayed in the gui
     */
    @NonNull
    private KitGUIData kitGUIData;

    /**
     * {@link KitItemsHolder} used to store information
     * about the contents of the kit
     */
    @NonNull
    private KitItemsHolder kitItemsHolder;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kit kit = (Kit) o;
        return id.equalsIgnoreCase(kit.id);
    }

    @Override
    public Kit clone() {
        return Kit
                .builder()
                .id(id)
                .name(name)
                .cooldown(cooldown)
                .kitGUIData(kitGUIData.clone())
                .kitItemsHolder(kitItemsHolder.clone())
                .build();
    }

    private static final Kit defaultKit = Kit
            .builder()
            .id("Default")
            .name("Default")
            .cooldown(100)
            .kitGUIData(KitGUIData
                    .builder()
                    .slot(0)
                    .displayItem(new ItemStack(Material.GREEN_SHULKER_BOX))
                    .displayLoreIfAvailable(List.of("Available kit"))
                    .displayLoreIfCooldown(List.of("Cooldown: {time}"))
                    .displayNoPermissionItem(new ItemStack(Material.RED_SHULKER_BOX))
                    .build())
            .kitItemsHolder(KitItemsHolder
                    .builder()
                    .helmet(new ItemStack(Material.DIAMOND_HELMET))
                    .chestplate(new ItemStack(Material.DIAMOND_CHESTPLATE))
                    .leggings(new ItemStack(Material.DIAMOND_LEGGINGS))
                    .boots(new ItemStack(Material.DIAMOND_BOOTS))
                    .offHand(new ItemStack(Material.TOTEM_OF_UNDYING))
                    .inventory(new ItemStack[]{new ItemStack(Material.OAK_LOG, 64), new ItemStack(Material.TORCH, 32)})
                    .build())
            .build();


    public static Kit getDefaultKit() {
        return defaultKit.clone();
    }
}
