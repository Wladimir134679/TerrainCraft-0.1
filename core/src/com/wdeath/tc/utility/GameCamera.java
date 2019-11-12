package com.wdeath.tc.utility;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameCamera extends OrthographicCamera {

    public float getWidth(){
        return viewportWidth * zoom;
    }

    public float getHeight(){
        return viewportHeight * zoom;
    }

    public float getX(){
        return position.x - getWidth() / 2;
    }

    public float getY(){
        return position.y - getHeight() / 2;
    }

    public GameCamera setX(float x){
        position.x = getWidth() / 2 - x;
        return this;
    }

    public GameCamera setY(float y){
        position.y = getHeight() / 2 - y;
        return this;
    }
}
