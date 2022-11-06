package com.lairon.plugin.xkits.data.serializer.kit;

import com.lairon.plugin.xkits.data.SectionNames;
import com.lairon.plugin.xkits.data.serializer.item.ItemSerializer;
import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.kit.KitGUIData;
import com.lairon.plugin.xkits.kit.KitItemsHolder;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import javax.inject.Inject;

public class KitSerializer {

    @Inject
    private ItemSerializer itemSerializer;

    public void serialiseKit(@NonNull Kit kit, @NonNull FileConfiguration config) {
        config.set(SectionNames.NAME, kit.getName());
        config.set(SectionNames.COOLDOWN, kit.getCooldown());
        serializeGUIData(kit.getKitGUIData(), config.createSection(SectionNames.GUI));
        serializeItemsHolder(kit.getKitItemsHolder(), config.createSection(SectionNames.ITEMS));

    }

    private ConfigurationSection serializeItemsHolder(KitItemsHolder kitItemsHolder, ConfigurationSection section) {
        itemSerializer.serializeItem(kitItemsHolder.getHelmet(), section.createSection(SectionNames.HELMET));
        itemSerializer.serializeItem(kitItemsHolder.getChestplate(), section.createSection(SectionNames.CHESTPLATE));
        itemSerializer.serializeItem(kitItemsHolder.getLeggings(), section.createSection(SectionNames.LEGGINGS));
        itemSerializer.serializeItem(kitItemsHolder.getBoots(), section.createSection(SectionNames.BOOTS));
        itemSerializer.serializeItem(kitItemsHolder.getOffHand(), section.createSection(SectionNames.OFF_HAND));

        for (int i = 0; i < kitItemsHolder.getInventory().length; i++) {
            itemSerializer.serializeItem(kitItemsHolder.getInventory()[i],
                    section.createSection( SectionNames.INVENTORY + "." + i));
        }

        return section;
    }

    private ConfigurationSection serializeGUIData(KitGUIData kitGUIData, ConfigurationSection section) {
        itemSerializer.serializeItem(kitGUIData.getDisplayItem(), section.createSection(SectionNames.DISPLAY_ITEM));
        section.set(SectionNames.LORE_IF_AVAILABLE, kitGUIData.getDisplayLoreIfAvailable());
        section.set(SectionNames.LORE_IF_COOLDOWN, kitGUIData.getDisplayLoreIfCooldown());
        itemSerializer.serializeItem(kitGUIData.getDisplayNoPermissionItem(), section.createSection(SectionNames.NO_PERMISSION_ITEM));
        return section;
    }

}
