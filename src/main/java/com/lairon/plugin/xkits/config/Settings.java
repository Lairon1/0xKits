package com.lairon.plugin.xkits.config;

import com.lairon.lairconfig.StorageClass;
import com.lairon.lairconfig.annotations.ConfigComment;
import com.lairon.lairconfig.annotations.ConfigPath;
import com.lairon.plugin.xkits.data.SerializationType;
import lombok.Getter;

@Getter
public class Settings extends StorageClass {

    @ConfigComment({"Serializer types:",
            "BINARY - Takes up much less space, but cannot be edited in the configuration file",
            "YAML - Takes up more space but allows editing in the configuration file"})
    @ConfigPath("SerializationType")
    private SerializationType serializationType = SerializationType.BINARY;


    @Override
    public String getPath() {
        return "Settings.";
    }
}
