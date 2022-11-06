package com.lairon.plugin.xkits.data.deserializer.item.impl;

import com.lairon.plugin.xkits.data.SectionNames;
import com.lairon.plugin.xkits.data.deserializer.item.ItemDeserializer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Base64;

/**
 * Deserializes the binary version
 * of the item in the {@link ItemStack}, has NBT support
 */
public class ItemBase64Deserializer implements ItemDeserializer {


    @Override
    public @Nullable ItemStack deserializeItemStack(ConfigurationSection section) {
        if (section == null || !section.contains(SectionNames.ITEM)) return null;
        byte[] itemBytes = Base64.getDecoder().decode(section.getString(SectionNames.ITEM));
        return Bukkit.getUnsafe().deserializeItem(itemBytes);
    }
}
