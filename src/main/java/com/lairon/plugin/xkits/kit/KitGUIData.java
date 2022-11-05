package com.lairon.plugin.xkits.kit;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
@Builder
public class KitGUIData {

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

    @NonNull
    private ItemStack displayNoPermissionItem;

}
