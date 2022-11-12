package com.lairon.plugin.xkits.playerdata;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public interface DataProvider {

    @Nullable
    AsyncTask<PlayerInfo> loadPlayerInfo(@NonNull String player);

    @NonNull
    AsyncTask<Boolean> savePlayerInfo(@NonNull PlayerInfo playerInfo);

}
