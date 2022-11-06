package com.lairon.plugin.xkits;

import ch.jalu.injector.Injector;
import ch.jalu.injector.InjectorBuilder;
import com.lairon.lairconfig.LairConfig;
import com.lairon.plugin.xkits.config.Messages;
import com.lairon.plugin.xkits.config.Settings;
import com.lairon.plugin.xkits.data.KitDataManager;
import com.lairon.plugin.xkits.data.deserializer.item.ItemDeserializer;
import com.lairon.plugin.xkits.data.deserializer.item.ItemDeserializerProvider;
import com.lairon.plugin.xkits.data.deserializer.kit.KitDeserializer;
import com.lairon.plugin.xkits.data.serializer.item.ItemSerializer;
import com.lairon.plugin.xkits.data.serializer.item.ItemSerializerProvider;
import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.kit.KitItemsHolder;
import com.lairon.plugin.xkits.registry.KitRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;

public final class KitsPlugin extends JavaPlugin {

    private Injector injector;
    private Logger logger = getSLF4JLogger();
    private KitRegistry kitRegistry;

    @Override
    public void onEnable() {
        initialize();

    }

    private void initialize() {
        injector = new InjectorBuilder()
                .addDefaultHandlers("com.lairon.plugin.xkits")
                .create();

        Settings settings = injector.getSingleton(Settings.class);
        Messages messages = injector.getSingleton(Messages.class);

        LairConfig config = new LairConfig(getDataFolder() + File.separator + "config.yml");

        try {
            config.registerStorageClass(settings);
            config.registerStorageClass(messages);
            config.reload();
        } catch (Exception | Error throwable) {
            logger.error("An error occurred while reloading the configuration file, fix it and use /kits reload");
            throwable.printStackTrace();
        }

        injector.register(KitsPlugin.class, this);
        injector.register(Logger.class, logger);
        injector.register(LairConfig.class, config);

        kitRegistry = injector.getSingleton(KitRegistry.class);

        injector.registerProvider(ItemDeserializer.class, ItemDeserializerProvider.class);
        injector.registerProvider(ItemSerializer.class, ItemSerializerProvider.class);

        injector.getSingleton(KitDeserializer.class);

        KitDataManager dataManager = injector.getSingleton(KitDataManager.class);
        dataManager.saveDefaultKitIfExist();
        dataManager.reloadKits();

        List<Kit> kits = kitRegistry.getKits();
        String[] kitsIDs = new String[kits.size()];

        for (int i = 0; i < kitsIDs.length; i++) {
            kitsIDs[i] = kits.get(i).getId();
        }

        logger.info("Registered kits: " + String.join(", ", kitsIDs));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
