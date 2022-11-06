package com.lairon.plugin.xkits.data.serializer.item.impl;

import com.lairon.plugin.xkits.data.SectionNames;
import com.lairon.plugin.xkits.data.serializer.item.ItemSerializer;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class ItemYamlSerializer implements ItemSerializer {

    @Override
    public void serializeItem(ItemStack itemStack, @NonNull ConfigurationSection section) {
        section.set(SectionNames.ITEM, itemStack);
    }
}
