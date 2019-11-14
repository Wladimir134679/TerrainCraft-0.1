package com.wdeath.tc.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.wdeath.tc.Assets;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.world.WorldFilters;
import com.wdeath.tc.world.WorldMap;
import com.wdeath.tc.world.objects.AbstractAliveObjectWorld;
import com.wdeath.tc.world.objects.ObjectBodyWorld;

public class PlayerObjectWorld extends AbstractAliveObjectWorld {

    private PlayerData playerData;
    private Sprite sprite;
    private WorldMap worldMap;

    public PlayerObjectWorld(PlayerData playerData, WorldMap worldMap){
        this.playerData = playerData;
        this.worldMap = worldMap;
        TextureRegion tex = Assets.get("ground");
        sprite = new Sprite(tex);
        init();
        setPosition(worldMap.getSpawnPoint("player").x, worldMap.getSpawnPoint("player").y);
    }

    @Override
    public Vector2 getPositionCenter() {
        return mainBody.getPosition();
    }

    @Override
    public void setPosition(float x, float y) {
        mainBody.setTransform(x, y, mainBody.getAngle());
        limbsBody[0].setTransform(x, y, limbsBody[0].getAngle());
    }

    @Override
    public Vector2 getAABBRectangle() {
        return null;
    }

    @Override
    public void draw(GameCanvas gameCanvas) {
        sprite.setPosition(getPositionCenter().x - PlayerData.WIDTH/2, getPositionCenter().y - PlayerData.HEIGHT/2);
        sprite.setSize(PlayerData.WIDTH, PlayerData.HEIGHT);
        sprite.setOrigin(PlayerData.WIDTH/2, PlayerData.HEIGHT/2);
        sprite.draw(gameCanvas.batch);
//        gameCanvas.batch.draw(tex, getPositionCenter().x - PlayerData.WIDTH/2, getPositionCenter().y - PlayerData.HEIGHT/2, PlayerData.WIDTH, PlayerData.HEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public int getNumLimb() {
        return 1;
    }

    @Override
    public void initMainBody() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;

        mainBody = worldMap.world.createBody(def);
    }

    @Override
    public void initLimbsBody() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        limbsBody[0] = worldMap.world.createBody(def);
    }

    @Override
    public void initMainFixture() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PlayerData.WIDTH / 2, PlayerData.HEIGHT / 2);

        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.filter.groupIndex = WorldFilters.GROUP_PLAYER;
        def.density = 1f;
        def.friction = 0.1f;

        mainFixture = mainBody.createFixture(def);
    }

    public Body getLeg(){
        return limbsBody[0];
    }

    @Override
    public void initLimbsFixture() {
        CircleShape shape = new CircleShape();
        shape.setRadius(PlayerData.WIDTH / 2);

        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.filter.groupIndex = WorldFilters.GROUP_PLAYER;
        def.friction = 2f;
        def.density = 1f;

        limbsFixture[0] = limbsBody[0].createFixture(def);
    }

    @Override
    public void initJoin() {
        connectionBodyAndLimb(limbsBody[0], new Vector2(0, -PlayerData.HEIGHT / 2), new Vector2(), worldMap);
    }
}
