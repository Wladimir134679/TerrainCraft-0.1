package com.wdeath.tc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.wdeath.tc.Assets;
import com.wdeath.tc.item.Item;
import com.wdeath.tc.item.blocks.GroundBlock;
import com.wdeath.tc.utility.GameCanvas;

public class CursorWorld {

    public int x, y;
    public Sprite sprite;
    private Vector3 tmpVector = new Vector3();
    private GameScreen gameScreen;
    public boolean isLeft = false, isRight = false;

    public CursorWorld(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        sprite = new Sprite();
        sprite.setRegion(Assets.get("ground"));
        sprite.setAlpha(0.2f);
        sprite.setSize(1, 1);
    }

    public void render(GameCanvas canvas){
        tmpVector = canvas.getCamera().unproject(tmpVector.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        x = (int) tmpVector.x;
        y = (int) tmpVector.y;

        sprite.setPosition(x, y);
        sprite.draw(canvas.batch);
        set();
    }

    private void set(){
        if(isRight){
            isRight = false;

            int id = Item.itemsClass.get(GroundBlock.class);
//            gameScreen.getWorldMap().getLayerBlock().setBlock(x, y, id);
        }
        if(isLeft){
            isLeft = false;
//            gameScreen.getWorldMap().getLayerBlock().setBlock(x, y, 0);
        }
    }
}
