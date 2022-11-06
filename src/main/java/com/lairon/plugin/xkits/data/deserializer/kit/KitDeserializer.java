package com.lairon.plugin.xkits.data.deserializer.kit;

import com.lairon.plugin.xkits.data.SectionNames;
import com.lairon.plugin.xkits.data.deserializer.item.ItemDeserializer;
import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.kit.KitGUIData;
import com.lairon.plugin.xkits.kit.KitItemsHolder;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Set;

public class KitDeserializer {

    @Inject
    private ItemDeserializer itemDeserializer;

    @Inject
    private Logger logger;

    /**
     * Deserializes your kit from a
     * Yaml config file to a {@link Kit}
     */
    @NonNull
    public Kit deserializeKit(@NonNull FileConfiguration config, @NonNull String id) {
        return Kit
                .builder()
                .id(id)
                .name(config.getString(SectionNames.NAME))
                .cooldown(config.getInt(SectionNames.COOLDOWN))
                .kitGUIData(deserializeGUIData(config.getConfigurationSection(SectionNames.GUI)))
                .kitItemsHolder(deserializeItems(config.getConfigurationSection(SectionNames.ITEMS)))
                .build();
    }

    @NonNull
    private KitGUIData deserializeGUIData(@NonNull ConfigurationSection section) {
        return KitGUIData
                .builder()
                .slot(section.getInt(SectionNames.SLOT))
                .displayItem(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.DISPLAY_ITEM)))
                .displayLoreIfAvailable(section.getStringList(SectionNames.LORE_IF_AVAILABLE))
                .displayLoreIfCooldown(section.getStringList(SectionNames.LORE_IF_COOLDOWN))
                .displayNoPermissionItem(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.NO_PERMISSION_ITEM)))
                .build();
    }

    @NonNull
    private KitItemsHolder deserializeItems(@NonNull ConfigurationSection section) {
        KitItemsHolder.KitItemsHolderBuilder builder = KitItemsHolder.builder();

        builder
                .helmet(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.HELMET)))
                .chestplate(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.CHESTPLATE)))
                .leggings(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.LEGGINGS)))
                .boots(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.BOOTS)))
                .offHand(itemDeserializer.deserializeItemStack(section.getConfigurationSection(SectionNames.OFF_HAND)))
                .inventory(deserializeInventory(section.getConfigurationSection(SectionNames.INVENTORY)));


        return builder.build();
    }

    private ItemStack[] deserializeInventory(@NonNull ConfigurationSection section) {
        Set<String> slots = section.getKeys(false);
        HashMap<Integer, ItemStack> slotItem = new HashMap<>();

        for (String stringSlot : slots) {
            int slot;
            try {
                slot = Integer.parseInt(stringSlot);
            } catch (Exception e) {
                logger.error(stringSlot + " slot is not an available inventory slot");
                continue;
            }
            slotItem.put(slot, itemDeserializer.deserializeItemStack(section.getConfigurationSection(stringSlot)));
        }

        int size = 0;

        for (Integer integer : slotItem.keySet()) {
            size = Math.max(integer, size);
        }

        ItemStack[] items = new ItemStack[size + 1];
        slotItem.forEach((integer, itemStack) -> items[integer] = itemStack);
        return items;
    }

}
