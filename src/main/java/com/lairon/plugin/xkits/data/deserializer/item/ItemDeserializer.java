package com.lairon.plugin.xkits.data.deserializer.item;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface ItemDeserializer {

    /**
     * Deserializes a ItemStack from a ConfigurationSection into an object
     *
     * @param section -Section with a whale from the config
     * @return Ready {@link ItemStack} or null if there was an error in the config
     */
    @Nullable
    ItemStack deserializeItemStack(ConfigurationSection section);

}
