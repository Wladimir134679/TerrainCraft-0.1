package com.wdeath.tc.item.blocks;

import com.wdeath.tc.item.Block;

public class GroundBlock extends Block {

    @Override
    public String getTextureWorld() {
        return "ground";
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public String getTexture() {
        return "ground";
    }

    @Override
    public String getName() {
        return "Земля";
    }
}
