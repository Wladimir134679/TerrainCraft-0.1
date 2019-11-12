package com.wdeath.tc.utility;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameCanvas {

    private GameCamera camera;
    public final SpriteBatch batch;

    public GameCanvas(){
        camera = new GameCamera();
        batch = new SpriteBatch();
    }

    public void begin(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    public void end(){
        batch.end();
    }

    public GameCamera getCamera(){
        return camera;
    }
}
