package com.lairon.plugin.xkits.data;

import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.data.deserializer.KitDeserializer;
import lombok.NonNull;

import javax.inject.Inject;

public class KitDataManager {

    @Inject
    private KitDeserializer deserializer;

    /**
     * Loads all kits from a configuration file
     */
    @NonNull
    public Kit[] loadKits() {
        return null;
    }

    /**
     * Saves the kit to your file
     */
    public void saveKit(@NonNull Kit kit) {

    }
}
