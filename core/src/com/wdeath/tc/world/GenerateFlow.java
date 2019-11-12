package com.wdeath.tc.world;

import com.wdeath.tc.item.Item;
import com.wdeath.tc.item.blocks.GroundBlock;

public class GenerateFlow {

    public void gen(WorldMap world){
        int ch = world.height / 2;
        int id = Item.itemsClass.get(GroundBlock.class);
        for(int x = 0; x < world.width; x++){
            for(int y = 0; y < ch; y++){
                world.getLayerBlock().setBlock(x, y, id);
            }
        }
    }
}
