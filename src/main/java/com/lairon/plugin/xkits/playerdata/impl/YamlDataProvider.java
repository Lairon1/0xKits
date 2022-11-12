package com.lairon.plugin.xkits.playerdata.impl;

import com.google.common.io.Files;
import com.lairon.plugin.xkits.playerdata.AsyncTask;
import com.lairon.plugin.xkits.playerdata.DataProvider;
import com.lairon.plugin.xkits.playerdata.PlayerInfo;
import lombok.NonNull;
import org.bspfsystems.yamlconfiguration.configuration.ConfigurationSection;
import org.bspfsystems.yamlconfiguration.configuration.InvalidConfigurationException;
import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class YamlDataProvider implements DataProvider {

    private Plugin plugin;

    private File file;
    private FileConfiguration config;

    public YamlDataProvider(Plugin plugin) throws IOException, InvalidConfigurationException {
        this.plugin = plugin;
        reloadFiles();
    }

    private void reloadFiles() throws IOException, InvalidConfigurationException {
        if (file == null) file = new File(plugin.getDataFolder() + File.separator + "players.yml");
        if (!file.exists()) {
            Files.createParentDirs(file);
            file.createNewFile();
        }
        if (config == null) config = YamlConfiguration.loadConfiguration(file);
        else config.load(file);
    }

    private void saveFiles() throws IOException {
        if (file != null && config != null) config.save(file);
    }

    @Override
    public @Nullable AsyncTask<PlayerInfo> loadPlayerInfo(@NonNull String playerName) {
        return new AsyncTask<>(plugin, () -> {
            try {
                reloadFiles();
                String player = playerName.toLowerCase();
                if (!config.contains(player)) return null;
                ConfigurationSection section = config.getConfigurationSection(player);
                HashMap<String, Long> kitsCooldown = new HashMap<>();
                for (String kitId : section.getKeys(false)) {
                    kitsCooldown.put(kitId, section.getLong(kitId));
                }
                return PlayerInfo.builder().name(player).kitsCooldown(kitsCooldown).build();
            } catch (Exception e) {
                return null;
            }
        });
    }

    @Override
    public @NonNull AsyncTask<Boolean> savePlayerInfo(@NonNull PlayerInfo playerInfo) {
        return new AsyncTask<>(plugin, () -> {
            try {
                config.set(playerInfo.getName().toLowerCase(), playerInfo.getKitsCooldown());
                saveFiles();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }


}
