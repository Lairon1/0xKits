package com.lairon.plugin.xkits.exception.registry;

import com.lairon.plugin.xkits.exception.KitException;
import com.lairon.plugin.xkits.kit.Kit;
import com.lairon.plugin.xkits.registry.KitRegistry;


/**
 * Discarded when a kit with the same ID
 * is already registered in {@link KitRegistry}
 */
public class KitAlreadyRegisteredException extends KitException {

    public KitAlreadyRegisteredException(Kit kit) {
        super("Kit " + kit.getId() + " is already registered.");
    }

}
