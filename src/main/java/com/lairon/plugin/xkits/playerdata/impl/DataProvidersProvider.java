package com.lairon.plugin.xkits.playerdata.impl;

import com.lairon.plugin.xkits.config.DataProviderSettings;
import com.lairon.plugin.xkits.playerdata.DataProvider;
import com.lairon.plugin.xkits.playerdata.impl.sql.MySQLDataProvider;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Provider;

public class DataProvidersProvider implements Provider<DataProvider> {

    @Inject
    private DataProviderSettings settings;
    @Inject
    private Plugin plugin;

    @Override
    public DataProvider get() {
        return switch (settings.getDataProviderType()) {
            case YAML -> new YamlDataProvider(plugin);
            case MY_SQL -> new MySQLDataProvider();
        };
    }

}
