package com.wdeath.tc.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.wdeath.tc.utility.GameCanvas;

public class WorldMap {

    public static final int SIZE_BLOCK = 32;

    public final int width, height;
    private final LayerBlock layerBlock;
    public final World world;
    public final Box2DDebugRenderer box2DDebugRenderer;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;

        world = new World(new Vector2(0, -9.1f), false);
        box2DDebugRenderer = new Box2DDebugRenderer();

        layerBlock = new LayerBlock(this);
    }

    public void render(GameCanvas canvas){
        layerBlock.render(canvas);
    }

    public void debug(GameCanvas canvas){
        box2DDebugRenderer.render(world, canvas.getCamera().combined);
    }

    public LayerBlock getLayerBlock(){
        return layerBlock;
    }
}
