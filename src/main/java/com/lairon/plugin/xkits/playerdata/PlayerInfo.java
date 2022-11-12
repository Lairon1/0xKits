package com.lairon.plugin.xkits.playerdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NonNull
public class PlayerInfo {

    private String name;
    private Map<String, Long> kitsCooldown;

    public Player toPlayer() {
        return Bukkit.getPlayerExact(name);
    }

}
