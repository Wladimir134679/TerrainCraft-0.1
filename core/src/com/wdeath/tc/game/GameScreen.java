package com.wdeath.tc.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.World;
import com.wdeath.tc.item.Item;
import com.wdeath.tc.item.ItemLoader;
import com.wdeath.tc.item.blocks.GroundBlock;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.utility.Perlin2D;
import com.wdeath.tc.world.GenerateFlow;
import com.wdeath.tc.world.GenerateWorld;
import com.wdeath.tc.world.WorldMap;

import java.util.Random;

public class GameScreen implements Screen {

    private GameCanvas canvas;
    private GameCanvas screen;
    private BitmapFont fontInfo;
    private WorldMap worldMap;

    @Override
    public void show() {
        canvas = new GameCanvas();
        if(Gdx.app.getType() == Application.ApplicationType.Android){
            canvas.getCamera().setToOrtho(
                    false,
                    Gdx.graphics.getWidth() / Gdx.graphics.getDensity() / WorldMap.SIZE_BLOCK,
                    Gdx.graphics.getHeight() / Gdx.graphics.getDensity() / WorldMap.SIZE_BLOCK);
        }else{
            canvas.getCamera().setToOrtho(false,
                    Gdx.graphics.getWidth() / WorldMap.SIZE_BLOCK,
                    Gdx.graphics.getHeight() / WorldMap.SIZE_BLOCK);
        }

        screen = new GameCanvas();
        screen.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        fontInfo = new BitmapFont();

        ItemLoader.init();
        worldMap = new WorldMap(900, 400);

        GenerateFlow generateFlow = new GenerateFlow();
        generateFlow.gen(worldMap);
//        GenerateWorld generateWorld = new GenerateWorld(100032);
//        generateWorld.generate(worldMap);
    }

    @Override
    public void render(float delta) {
        canvas.begin();
        worldMap.render(canvas);
        canvas.end();

//        worldMap.debug(canvas);

        screen.begin();
        String fps = "FPS: " + Gdx.graphics.getFramesPerSecond();
        fontInfo.draw(screen.batch, fps, 10, fontInfo.getCapHeight() + 10);
        screen.end();

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            canvas.getCamera().zoom += 0.1f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            canvas.getCamera().zoom -= 0.1f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.TAB)){
            canvas.getCamera().zoom = 1;
        }

        float speed = 0.1f * canvas.getCamera().zoom;
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            canvas.getCamera().position.y += speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            canvas.getCamera().position.y -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            canvas.getCamera().position.x -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            canvas.getCamera().position.x += speed;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
