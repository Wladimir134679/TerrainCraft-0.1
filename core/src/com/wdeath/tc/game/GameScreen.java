package com.wdeath.tc.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.wdeath.tc.item.ItemLoader;
import com.wdeath.tc.player.PlayerData;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.world.GenerateFlow;
import com.wdeath.tc.world.WorldMap;

public class GameScreen implements Screen {

    private GameCanvas canvas;
    private GameCanvas screen;
    private BitmapFont fontInfo;
    private WorldMap worldMap;
    private CursorWorld cursorWorld;

    private PlayerData playerData;

    private boolean isDebug = true;

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
        worldMap = new WorldMap(400, 200);

        cursorWorld = new CursorWorld(this);

        Gdx.input.setInputProcessor(new InputListener(this));
    }

    @Override
    public void render(float delta) {
//        canvas.getCamera().position.set(playerObjectWorld.getPositionCenter(), 0);
        canvas.begin();
//        worldMap.render(canvas);
        cursorWorld.render(canvas);
        canvas.end();

        screen.begin();
        String fps = "FPS: " + Gdx.graphics.getFramesPerSecond();
//        String sX = "X: " + playerObjectWorld.getBody().getLinearVelocity().x;
//        String sY = "Y: " + playerObjectWorld.getBody().getLinearVelocity().y;
        fontInfo.draw(screen.batch, fps, 10, fontInfo.getCapHeight() + 10);
//        fontInfo.draw(screen.batch, sX, 10, fontInfo.getCapHeight() + 10 + fontInfo.getCapHeight() + 10);
//        fontInfo.draw(screen.batch, sY, 10, fontInfo.getCapHeight() + 10 + fontInfo.getCapHeight() + 10 + fontInfo.getCapHeight() + 10);
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

//        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)){
//            playerObjectWorld.getLeg().setFixedRotation(false);
//        }else{
//            playerObjectWorld.getLeg().setFixedRotation(true);
//        }
//
//        if(Gdx.input.isKeyJustPressed(Input.Keys.F3))
//            isDebug = !isDebug;
//
//        float speed = 5f;
//        if(Gdx.input.isKeyPressed(Input.Keys.W)){
//           playerObjectWorld.getBody().applyForceToCenter(0, speed * 5, false);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.S)){
//            playerObjectWorld.getBody().applyForceToCenter(0, -speed, false);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.A)){
////            playerObjectWorld.getBody().applyForceToCenter(-speed, 0, false);
//            playerObjectWorld.getLeg().applyTorque(speed * 2, false);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.D)){
//            playerObjectWorld.getLeg().applyTorque(-speed * 5, false);
//        }
//        Vector2 lin = playerObjectWorld.getBody().getLinearVelocity();
//        if(Math.abs(lin.x) < 0.01f){
//            lin.x = 0;
//        }
//        if(Math.abs(lin.y) < 0.01f){
//            lin.y = 0;
//        }
//
//        if(Math.abs(lin.x) > 10f){
//            lin.x = 10f * (lin.x > 0 ? 1 : -1);
//        }
//
//        playerObjectWorld.getBody().setLinearVelocity(lin);
    }

    public CursorWorld getCursorWorld() {
        return cursorWorld;
    }

    public WorldMap getWorldMap() {
        return worldMap;
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
