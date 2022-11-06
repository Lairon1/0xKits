package com.lairon.plugin.xkits.data.deserializer.item.impl;

import com.lairon.plugin.xkits.data.SectionNames;
import com.lairon.plugin.xkits.data.deserializer.item.ItemDeserializer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Deserializes the YAML version
 * of the item in the {@link ItemStack}, has NBT support
 */
public class ItemYamlDeserializer implements ItemDeserializer {

    @Override
    public @Nullable ItemStack deserializeItemStack(ConfigurationSection section) {
        if (section == null || !section.contains(SectionNames.ITEM)) return null;
        return section.getItemStack(SectionNames.ITEM);
    }
}
