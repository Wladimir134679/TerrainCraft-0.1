package com.wdeath.tc.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PhysicsGround {

    private Body body;
    private Fixture[][] fixtures;
    private LayerBlock layerBlock;

    public PhysicsGround(LayerBlock layerBlock) {
        this.layerBlock = layerBlock;

        fixtures = new Fixture[layerBlock.getWorld().width][layerBlock.getWorld().height];
    }
}
