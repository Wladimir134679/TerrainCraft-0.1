package com.wdeath.tc.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.wdeath.tc.Assets;
import com.wdeath.tc.item.Block;
import com.wdeath.tc.item.Item;
import com.wdeath.tc.utility.GameCanvas;

public class LayerBlock {

    private WorldMap worldMap;
    private int[][] blocks;
    private Body body;
    private Fixture[][] fixtures;

    public LayerBlock(WorldMap worldMap) {
        this.worldMap = worldMap;

        blocks = new int[worldMap.width][worldMap.height];
        fixtures = new Fixture[worldMap.width][worldMap.height];

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(0, 0);

        body = worldMap.world.createBody(def);
    }

    public void setBlock(int x, int y, int id){
        if(border(x, y))
            return;
        blocks[x][y] = id;
        if(id != 0)
            createBox(x, y);
    }

    public int getBlock(int x, int y){
        if(border(x, y))
            return 0;
        return blocks[x][y];
    }

    private boolean border(int x, int y){
        if(x < 0 || y < 0 || x >= worldMap.width || y >= worldMap.height)
            return true;
        return false;
    }

    private void createBox(int x, int y){
        FixtureDef def  = new FixtureDef();
        ChainShape chainShape = new ChainShape();
        float[] vec = new float[4 * 2];
        vec[0] = x;
        vec[1] = y;

        vec[2] = x + 1;
        vec[3] = y;

        vec[4] = x + 1;
        vec[5] = y + 1;

        vec[6] = x;
        vec[7] = y + 1;

        chainShape.createLoop(vec);
        def.shape = chainShape;
        def.friction = 0.1f;

        Fixture fixture = body.createFixture(def);
        fixtures[x][y] = fixture;
    }

    public void render(GameCanvas canvas){
        int x1 = (int)Math.floor(canvas.getCamera().getX() );
        int y1 = (int)Math.floor(canvas.getCamera().getY() );

        if(x1 < 0)
            x1 = 0;
        if(y1 < 0)
            y1 = 0;
        if(x1 >= worldMap.width)
            x1 = worldMap.width - 1;
        if(y1 >= worldMap.height)
            y1 = worldMap.height - 1;

        int w1 = (int) Math.ceil(canvas.getCamera().getWidth() ) + 1;
        int h1 = (int) Math.ceil(canvas.getCamera().getHeight() ) + 1;

        int x2 = x1 + w1;
        int y2 = y1 + h1;

        if(x2 < 0)
            x2 = 0;
        if(y2 < 0)
            y2 = 0;
        if(x2 >= worldMap.width)
            x2 = worldMap.width - 1;
        if(y2 >= worldMap.height)
            y2 = worldMap.height - 1;

        for(int x = x1; x < x2; x++){
            for(int y = y1; y < y2; y++){
                int id = blocks[x][y];
                if(id == 0)
                    continue;
                Block block = (Block)Item.items[id];
                String name = block.getTextureWorld();
                TextureRegion texture = Assets.get(name);
                canvas.batch.draw(texture, x, y, 1, 1);
            }
        }
    }
}
