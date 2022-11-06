package com.lairon.plugin.xkits.data.serializer.item;

import com.lairon.plugin.xkits.config.Settings;
import com.lairon.plugin.xkits.data.serializer.item.impl.ItemBase64Serializer;
import com.lairon.plugin.xkits.data.serializer.item.impl.ItemYamlSerializer;

import javax.inject.Inject;
import javax.inject.Provider;

public class ItemSerializerProvider implements Provider<ItemSerializer> {

    @Inject
    private Settings settings;

    @Override
    public ItemSerializer get() {
        return switch (settings.getItemSerializationType()) {
            case YAML -> new ItemYamlSerializer();
            case BASE64 -> new ItemBase64Serializer();
        };
    }
}
