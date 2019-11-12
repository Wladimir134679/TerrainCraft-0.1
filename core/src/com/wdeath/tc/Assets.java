package com.wdeath.tc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Assets {

    public static HashMap<String, TextureRegion> textures;

    public static void load(){
        textures = new HashMap<>();
        add("test", "test.png");
        add("ground", "ground.png");
    }

    private static void add(String name, String path){
        textures.put(name, new TextureRegion(new Texture(Gdx.files.internal(path))));
    }

    public static TextureRegion get(String name){
        return textures.get(name);
    }
}
