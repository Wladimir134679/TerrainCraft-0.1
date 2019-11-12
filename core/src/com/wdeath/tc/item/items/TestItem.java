package com.wdeath.tc.item.items;

import com.wdeath.tc.item.Item;

public class TestItem extends Item {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String getTexture() {
        return "test";
    }

    @Override
    public String getName() {
        return "Test item";
    }
}
