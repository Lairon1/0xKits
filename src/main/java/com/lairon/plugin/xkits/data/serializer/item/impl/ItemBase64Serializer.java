package com.lairon.plugin.xkits.data.serializer.item.impl;

import com.lairon.plugin.xkits.data.SectionNames;
import com.lairon.plugin.xkits.data.serializer.item.ItemSerializer;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Base64;

public class ItemBase64Serializer implements ItemSerializer {

    @Override
    public void serializeItem(ItemStack itemStack, @NonNull ConfigurationSection section) {
        if (itemStack == null || itemStack.getType().isAir()) {
            section.set(SectionNames.ITEM, null);
            return;
        }
        section.set(SectionNames.ITEM, Base64.getEncoder().encodeToString(Bukkit.getUnsafe().serializeItem(itemStack)));

    }

}
