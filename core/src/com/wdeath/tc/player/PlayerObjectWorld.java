package com.wdeath.tc.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.wdeath.tc.Assets;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.world.WorldMap;
import com.wdeath.tc.world.objects.ObjectBodyWorld;

public class PlayerObjectWorld extends ObjectBodyWorld {

    private PlayerData playerData;
    private Fixture fixture;
    private Sprite sprite;

    public PlayerObjectWorld(PlayerData playerData, WorldMap worldMap){
        this.setWorldMap(worldMap);
        this.playerData = playerData;
        createBody();
        TextureRegion tex = Assets.get("ground");
        sprite = new Sprite(tex);
    }

    private void createBody(){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(getWorldMap().getSpawnPoint("player"));
        def.fixedRotation = true;

        Body body = getWorldMap().world.createBody(def);
        this.setBody(body);
        createFixture();
    }

    private void createFixture(){
        FixtureDef def = new FixtureDef();
        def.density = 0.5f;
        def.restitution = 0.1f;
        def.friction = 0.2f;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PlayerData.WIDTH / 2, PlayerData.HEIGHT / 2);

        def.shape = shape;
        fixture = getBody().createFixture(def);
    }

    @Override
    public void draw(GameCanvas gameCanvas) {
        sprite.setPosition(getPositionCenter().x - PlayerData.WIDTH/2, getPositionCenter().y - PlayerData.HEIGHT/2);
        sprite.setSize(PlayerData.WIDTH, PlayerData.HEIGHT);
        sprite.setOrigin(PlayerData.WIDTH/2, PlayerData.HEIGHT/2);
        sprite.setRotation((float)Math.toDegrees(getBody().getAngle()));
        sprite.draw(gameCanvas.batch);
//        gameCanvas.batch.draw(tex, getPositionCenter().x - PlayerData.WIDTH/2, getPositionCenter().y - PlayerData.HEIGHT/2, PlayerData.WIDTH, PlayerData.HEIGHT);
    }

    @Override
    public void update() {

    }
}
