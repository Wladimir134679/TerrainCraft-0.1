package com.wdeath.tc.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.world.WorldMap;

public abstract class ObjectBodyWorld extends AbstractObjectWorld {

    private Body body;
    private WorldMap worldMap;

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public Vector2 getPositionCenter() {
        return body.getPosition();
    }

    @Override
    public void setPosition(float x, float y) {
        body.getPosition().set(x, y);
    }

    @Override
    public Vector2 getAABBRectangle() {
        return null;
    }
}
