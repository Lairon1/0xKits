package com.lairon.plugin.xkits.data.serializer.item;

import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public interface ItemSerializer {

    void serializeItem(ItemStack itemStack, @NonNull ConfigurationSection section);

}
