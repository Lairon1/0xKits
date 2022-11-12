package com.lairon.plugin.xkits.playerdata.impl.sql;

import com.lairon.plugin.xkits.playerdata.AsyncTask;
import com.lairon.plugin.xkits.playerdata.PlayerInfo;
import com.lairon.plugin.xkits.playerdata.DataProvider;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;

public abstract class SQLDataProvider implements DataProvider {

    @Override
    public @Nullable AsyncTask<PlayerInfo> loadPlayerInfo(@NonNull String player) {
        return null;
    }

    @Override
    public @NonNull AsyncTask<Boolean> savePlayerInfo(@NonNull PlayerInfo playerInfo) {
        return null;
    }

    public abstract Connection getConnection();

}
