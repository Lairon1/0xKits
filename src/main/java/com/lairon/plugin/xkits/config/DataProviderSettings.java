package com.lairon.plugin.xkits.config;

import com.lairon.lairconfig.StorageClass;
import com.lairon.lairconfig.annotations.ConfigComment;
import com.lairon.lairconfig.annotations.ConfigPath;
import com.lairon.plugin.xkits.playerdata.DataProviderType;
import lombok.Getter;

@Getter
public class DataProviderSettings extends StorageClass {

    @ConfigPath("Type")
    @ConfigComment({
            "What type of data provider to use",
            "Types:",
            "   YAML - configuration file",
            "          This type can cause memory leaks,",
            "          if you encounter them,",
            "          please do not contact me for help",
            "",
            "   MY_SQL - MySQL database"})
    private DataProviderType dataProviderType = DataProviderType.YAML;


    @Override
    public String getPath() {
        return "DataProvider.";
    }
}
