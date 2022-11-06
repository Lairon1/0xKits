package com.lairon.plugin.xkits.data;

import com.google.common.io.Files;
import com.lairon.plugin.xkits.KitsPlugin;
import com.lairon.plugin.xkits.data.deserializer.kit.KitDeserializer;
import com.lairon.plugin.xkits.data.serializer.kit.KitSerializer;
import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.registry.KitRegistry;
import lombok.NonNull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KitDataManager {

    @Inject
    private KitsPlugin plugin;

    @Inject
    private KitDeserializer kitDeserializer;

    @Inject
    private KitSerializer kitSerializer;

    @Inject
    private KitRegistry kitRegistry;

    /**
     * Loads all kits from a configuration file
     */
    @NonNull
    public Kit[] loadKits() {
        File dir = new File(plugin.getDataFolder() + File.separator + "kits");

        File[] files = dir.listFiles();
        Kit[] kits = new Kit[files.length];

        for (int i = 0; i < files.length; i++) {
            kits[i] = loadKit(files[i]);
        }

        return kits;
    }

    /**
     * Load kit from file
     */
    public Kit loadKit(@NonNull File file) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return kitDeserializer.deserializeKit(configuration, file.getName().replace(".yml", ""));
    }

    /**
     * Reload all kits of {@link KitRegistry}
     */
    public void reloadKits() {
        kitRegistry.clear();
        for (Kit kit : loadKits()) {
            kitRegistry.registerKit(kit);
        }
    }

    /**
     * Saves the kit to your file
     */
    public void saveKit(@NonNull Kit kit) throws IOException {
        File file = new File(plugin.getDataFolder() + File.separator + "kits" + File.separator + kit.getId() + ".yml");
        if (!file.exists()) {
            Files.createParentDirs(file);
            file.createNewFile();
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        kitSerializer.serialiseKit(kit, config);
        config.save(file);
    }

    public void saveDefaultKitIfExist() {
        if (!java.nio.file.Files.exists(Paths.get(plugin.getDataFolder() + File.separator + "kits"))) {
            try {
                saveKit(Kit.getDefaultKit());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
