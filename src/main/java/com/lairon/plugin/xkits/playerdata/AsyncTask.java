package com.lairon.plugin.xkits.playerdata;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class AsyncTask<T> {

    private T obj;
    private Consumer<T> consumer;

    private Plugin plugin;

    public AsyncTask(@NonNull Plugin plugin, @NonNull Callable<T> callable) {
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                obj = callable.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (consumer != null) consumerAccept();
        });
    }

    public void accept(@NonNull Consumer<T> consumer) {
        this.consumer = consumer;
        if (obj != null) consumerAccept();
    }

    private void consumerAccept() {
        Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(obj));
    }

}
