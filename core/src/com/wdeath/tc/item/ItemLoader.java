package com.wdeath.tc.item;

import com.wdeath.tc.item.blocks.GroundBlock;
import com.wdeath.tc.item.items.TestItem;

import java.util.HashMap;

public class ItemLoader {

    public static void init(){
        Item.items = new Item[1024];
        Item.itemsClass = new HashMap<>();

        add(new TestItem());
        add(new GroundBlock());
    }

    private static void add(Item item){
        Item a = Item.items[item.getId()];
        if(a != null){
            System.out.println("ERROR: id = " + item.getId());
        }
        Item.items[item.getId()] = item;
        Item.itemsClass.put(item.getClass(), item.getId());
    }
}
