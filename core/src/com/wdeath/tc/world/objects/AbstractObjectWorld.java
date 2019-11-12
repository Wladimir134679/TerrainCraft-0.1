package com.wdeath.tc.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.wdeath.tc.utility.GameCanvas;

public abstract class AbstractObjectWorld {

    public abstract Vector2 getPositionCenter();
    public abstract void setPosition(float x, float y);

    public abstract Vector2 getAABBRectangle();

    public abstract void draw(GameCanvas gameCanvas);
    public abstract void update();
}
