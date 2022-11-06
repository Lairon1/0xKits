package com.lairon.plugin.xkits.config;

import com.lairon.lairconfig.StorageClass;
import com.lairon.lairconfig.annotations.ConfigComment;
import com.lairon.lairconfig.annotations.ConfigPath;
import com.lairon.plugin.xkits.data.ItemSerializationType;
import lombok.Getter;

@Getter
public class Settings extends StorageClass {

    @ConfigComment({"Item serializer types:",
            "BASE64 - Takes up much less space, but cannot be edited in the configuration file. Has the support of the NBT",
            "         Can store large items like a shulker box of full items",
            "YAML - Takes up more space but allows editing in the configuration file. Has the support of the NBT"})
    @ConfigPath("SerializationType")
    private ItemSerializationType itemSerializationType = ItemSerializationType.BASE64;


    @Override
    public String getPath() {
        return "Settings.";
    }
}
