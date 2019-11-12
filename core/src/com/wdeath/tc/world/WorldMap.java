package com.wdeath.tc.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.world.objects.AbstractObjectWorld;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {

    public static final int SIZE_BLOCK = 32;

    public final int width, height;
    private final LayerBlock layerBlock;
    public final World world;
    public final Box2DDebugRenderer box2DDebugRenderer;

    private HashMap<String, Vector2> spawnPoint;

    private ArrayList<AbstractObjectWorld> objectWorlds;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;

        world = new World(new Vector2(0, -9.1f), false);
        box2DDebugRenderer = new Box2DDebugRenderer();

        layerBlock = new LayerBlock(this);

        objectWorlds = new ArrayList<>();
        spawnPoint = new HashMap<>();
    }

    public void render(GameCanvas canvas){
        layerBlock.render(canvas);
        world.step(1/60f, 4, 2);

        for(AbstractObjectWorld obj : objectWorlds){
            obj.update();
        }

        for(AbstractObjectWorld obj : objectWorlds){
            obj.draw(canvas);
        }
    }

    public void debug(GameCanvas canvas){
        box2DDebugRenderer.render(world, canvas.getCamera().combined);
    }

    public void addObject(AbstractObjectWorld obj){
        objectWorlds.add(obj);
    }

    public LayerBlock getLayerBlock(){
        return layerBlock;
    }

    public void addSpawnPoint(String name, Vector2 vec){
        spawnPoint.put(name, vec);
    }

    public Vector2 getSpawnPoint(String name){
        return spawnPoint.get(name);
    }
}
