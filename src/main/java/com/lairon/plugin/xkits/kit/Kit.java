package com.lairon.plugin.xkits.kit;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Kit {

    @NonNull
    private String id;

    @NonNull
    private String name;

    /**
     * How long does a player have to wait to take a kit again
     * if the value is -1 then there will be no delay
     */
    @NonNull
    private int cooldown;

    @NonNull
    private int slot;

    /**
     * {@link KitGUIData} used to store information that
     * will be displayed in the gui
     */
    @NonNull
    private KitGUIData kitGUIData;

    /**
     * {@link KitItemsHolder} used to store information
     * about the contents of the kit
     */
    @NonNull
    private KitItemsHolder kitItemsHolder;
}