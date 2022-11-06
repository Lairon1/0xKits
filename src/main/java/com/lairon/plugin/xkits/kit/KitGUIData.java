package com.lairon.plugin.xkits.kit;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class KitGUIData implements Cloneable{

    /**
     * The slot in which the item will lie
     */
    @NonNull
    private int slot;

    /**
     * The item that will be displayed if the player has access to this set
     * if the kit is available to the player, then lore
     * will be replaced by the value in {@link #getDisplayLoreIfAvailable()}
     * else the value from {@link #getDisplayLoreIfCooldown()} will be used
     */
    @NonNull
    private ItemStack displayItem;

    @NonNull
    private List<String> displayLoreIfAvailable;

    @NonNull
    private List<String> displayLoreIfCooldown;

    /**
     * If the player does not have permission to
     * take this kit, he will be shown an item from {@link #getDisplayNoPermissionItem()} ()}
     */
    @NonNull
    private ItemStack displayNoPermissionItem;


    @Override
    public KitGUIData clone() {
        return KitGUIData
                .builder()
                .slot(slot)
                .displayItem(displayItem.clone())
                .displayLoreIfAvailable(new ArrayList<>(displayLoreIfAvailable))
                .displayLoreIfCooldown(new ArrayList<>(displayLoreIfCooldown))
                .displayNoPermissionItem(displayNoPermissionItem.clone())
                .build();
    }
}
