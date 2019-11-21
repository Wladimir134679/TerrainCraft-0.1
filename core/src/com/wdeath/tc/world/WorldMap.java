package com.wdeath.tc.world;

import com.badlogic.gdx.math.Vector2;
import com.wdeath.tc.utility.GameCanvas;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {

    public static final int SIZE_BLOCK = 32;

    public final int width, height;

    private final HashMap<String, Vector2> spawnPoint;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;

        spawnPoint = new HashMap<>();
    }
}
