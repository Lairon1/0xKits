package com.lairon.plugin.xkits.data.deserializer;

import com.lairon.plugin.xkits.config.Settings;
import com.lairon.plugin.xkits.data.deserializer.impl.KitBinaryDeserializer;
import com.lairon.plugin.xkits.data.deserializer.impl.KitYamlDeserializer;

import javax.inject.Inject;
import javax.inject.Provider;

public class KitDeserializerDataProvider implements Provider<KitDeserializer> {

    @Inject
    private Settings settings;

    @Override
    public KitDeserializer get() {
        return switch (settings.getSerializationType()) {
            case BINARY -> new KitBinaryDeserializer();
            case YAML -> new KitYamlDeserializer();
        };
    }


}
