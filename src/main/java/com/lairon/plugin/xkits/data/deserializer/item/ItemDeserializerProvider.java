package com.lairon.plugin.xkits.data.deserializer.item;

import com.lairon.plugin.xkits.config.Settings;
import com.lairon.plugin.xkits.data.deserializer.item.impl.ItemBase64Deserializer;
import com.lairon.plugin.xkits.data.deserializer.item.impl.ItemYamlDeserializer;

import javax.inject.Inject;
import javax.inject.Provider;

public class ItemDeserializerProvider implements Provider<ItemDeserializer> {

    @Inject
    private Settings settings;

    @Override
    public ItemDeserializer get() {
        return switch (settings.getItemSerializationType()) {
            case BASE64 -> new ItemBase64Deserializer();
            case YAML -> new ItemYamlDeserializer();
        };
    }


}
