package com.lairon.plugin.xkits.exception.registry;

import com.lairon.plugin.xkits.exception.KitException;
import com.lairon.plugin.xkits.kit.Kit;

public class KitAlreadyRegisteredException extends KitException {

    public KitAlreadyRegisteredException(Kit kit) {
        super("Kit " + kit.getId() + " is already registered.");
    }

}
