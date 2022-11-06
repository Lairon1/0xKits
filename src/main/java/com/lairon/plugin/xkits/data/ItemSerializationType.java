package com.lairon.plugin.xkits.data;

public enum ItemSerializationType {

    /**
     * Serializer types:
     *  @see #BASE64 - Takes up much less space, but cannot be edited in the configuration file
     *  @see #YAML - Takes up more space but allows editing in the configuration file
     */
    YAML, BASE64;

}
