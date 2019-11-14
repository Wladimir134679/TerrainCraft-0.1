package com.wdeath.tc.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.wdeath.tc.utility.GameCanvas;
import com.wdeath.tc.world.WorldMap;

public abstract class AbstractAliveObjectWorld extends AbstractObjectWorld {

    protected Body mainBody;
    protected Body[] limbsBody;

    protected Fixture mainFixture;
    protected Fixture[] limbsFixture;

    public Body getBody(){
        return mainBody;
    }

    public abstract int getNumLimb();

    public void init(){
        limbsBody = new Body[getNumLimb()];
        limbsFixture = new Fixture[getNumLimb()];

        initMainBody();
        initLimbsBody();

        initMainFixture();
        initLimbsFixture();

        initJoin();
    }

    public abstract void initMainBody();
    public abstract void initLimbsBody();

    public abstract void initMainFixture();
    public abstract void initLimbsFixture();

    public abstract void initJoin();

    protected RevoluteJoint connectionBodyAndLimb(Body limb, Vector2 posB, Vector2 posL, WorldMap worldMap){
        RevoluteJointDef def = new RevoluteJointDef();
        def.bodyA = mainBody;
        def.bodyB = limb;
        def.localAnchorA.set(posB);
        def.localAnchorB.set(posL);
        def.enableLimit = false;
        def.enableMotor = false;
        def.collideConnected = false;

        RevoluteJoint join = (RevoluteJoint)worldMap.world.createJoint(def);
        return join;
    }
}
