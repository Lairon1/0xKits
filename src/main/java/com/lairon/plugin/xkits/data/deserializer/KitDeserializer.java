package com.lairon.plugin.xkits.data.deserializer;

import com.lairon.plugin.xkits.kit.Kit;
import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

public interface KitDeserializer {

    /**
     * Deserializes a bundle from a ConfigurationSection into an object
     *
     * @param section -Section with a whale from the config
     * @return Ready kit or null if there was an error in the config
     */
    @Nullable
    Kit deserializeKit(@NonNull ConfigurationSection section);

}
