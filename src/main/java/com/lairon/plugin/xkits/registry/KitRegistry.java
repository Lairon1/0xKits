package com.lairon.plugin.xkits.registry;

import com.lairon.plugin.xkits.exception.registry.KitAlreadyRegisteredException;
import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.data.KitDataManager;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class KitRegistry {

    private final List<Kit> registryKits = new ArrayList<>();

    /**
     * Use to embed your kit into a plugin
     * If you want to save your kit in config see {@link KitDataManager#saveKit(Kit)}
     *
     * @param kit - Kit for registration
     */
    public void registerKit(@NonNull Kit kit) {
        if (registryKits.contains(kit))
            throw new KitAlreadyRegisteredException(kit);
        registryKits.add(kit);
    }

    /**
     * Use to search for a kit by its ID
     *
     * @param id - Required kit id
     * @return The desired Kit or null
     */
    @Nullable
    public Kit getKitByID(@NonNull String id) {
        return registryKits
                .stream()
                .filter(kit -> kit.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public List<Kit> getKits(){
        return new ArrayList<>(registryKits);
    }
}
