package com.wdeath.tc.world;

import com.badlogic.gdx.math.RandomXS128;
import com.wdeath.tc.item.Item;
import com.wdeath.tc.item.blocks.GroundBlock;
import com.wdeath.tc.utility.Perlin2D;

import java.util.Random;

public class GenerateWorld {

    private long seed;
    private Random rnd;
    private WorldMap worldMap;

    public GenerateWorld(long seed) {
        this.seed = seed;
    }

    public void generate(WorldMap world){
        this.worldMap = world;
        rnd = new RandomXS128(seed);
        int[] heights = new int[world.width];
        createHeight(heights);

        int id = Item.itemsClass.get(GroundBlock.class);
        Perlin2D perlin2D = new Perlin2D(2);
        for(int x = 0; x < world.width; x++){
            for(int y = 0; y < heights[x]; y++){
                float v = perlin2D.noise(x / (float)30, y / (float)30, 10);
                if(v < 0)
                    world.getLayerBlock().setBlock(x, y, id);
            }
        }

        for(int x = 0; x < world.width; x++) {
            for (int y = heights[x] - 4; y < heights[x]; y++) {
                world.getLayerBlock().setBlock(x, y, id);
            }
        }
    }

    private void createHeight(int[] array){
        int start = worldMap.height / 2;
        int step = getStep();
        start += step;

        array[0] = start;
        for(int x = 1; x < worldMap.width; x++){
            step = getStep();
            array[x] = array[x - 1] + step;

            if (array[x] < 0)
                array[x] = 0;
            if (array[x] >= worldMap.height)
                array[x] = worldMap.height - 1;
        }

        int inter = rnd.nextInt(5) + 2;
        for(int i = 0; i < inter; i++){
            for(int x = 1; x < worldMap.width - 1; x++){
                int x1 = array[x - 1];
                int x2 = array[x + 1];
                array[x] = (x1 + x2) / 2;
            }
        }
    }

    private int getStep(){
        return rnd.nextInt(7) - 3;
    }
}
