package com.lairon.plugin.xkits;

import ch.jalu.injector.Injector;
import ch.jalu.injector.InjectorBuilder;
import com.lairon.lairconfig.LairConfig;
import com.lairon.plugin.xkits.config.Messages;
import com.lairon.plugin.xkits.config.Settings;
import com.lairon.plugin.xkits.data.KitDataManager;
import com.lairon.plugin.xkits.data.deserializer.KitDeserializer;
import com.lairon.plugin.xkits.data.deserializer.KitDeserializerDataProvider;
import com.lairon.plugin.xkits.registry.KitRegistry;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class KitsPlugin extends JavaPlugin {

    private Injector injector;

    @Override
    public void onEnable() {
        initialize();

    }

    private void initialize(){
        injector = new InjectorBuilder()
                .addDefaultHandlers("com.lairon.plugin.xkits")
                .create();

        Settings settings = new Settings();
        Messages messages = new Messages();

        LairConfig config = new LairConfig(getDataFolder() + File.separator + "config.yml");

        try{
            config.registerStorageClass(settings);
            config.registerStorageClass(messages);
            config.reload();
        }catch (Exception | Error throwable){
            getSLF4JLogger().error("An error occurred while reloading the configuration file, fix it and use /kits reload");
            throwable.printStackTrace();
        }

        injector.register(KitsPlugin.class, this);

        /* Config init */
        injector.register(Settings.class, settings);
        injector.register(Messages.class, messages);
        injector.register(LairConfig.class, config);

        injector.register(KitRegistry.class, new KitRegistry());

        injector.registerProvider(KitDeserializer.class, KitDeserializerDataProvider.class);
        injector.register(KitDataManager.class, new KitDataManager());




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
