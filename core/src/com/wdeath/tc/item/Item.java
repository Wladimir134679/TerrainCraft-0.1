package com.wdeath.tc.item;

import java.util.HashMap;

public abstract class Item {

    public static Item[] items;
    public static HashMap<Class<? extends Item>, Integer> itemsClass;

    public abstract int getId();
    public abstract String getTexture();
    public abstract String getName();

    public int getMaxStack(){
        return 32;
    }
}
