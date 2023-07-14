package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

public class GameStage extends Stage {

    private final IntSet KEYS;

    public GameStage() {
        super(ActorUtil.getInstance().getGameViewport(), AssetUtil.getInstance().getSpriteBatch());
        this.KEYS = new IntSet();
        addListener(initAndGetUserInputListener());
    }

    private InputListener initAndGetUserInputListener() {
        return new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                super.keyDown(event, keycode);
                KEYS.add(keycode);
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                super.keyUp(event, keycode);
                KEYS.remove(keycode);
                return true;
            }

        };
    }

    public IntSet getKeys() {
        return KEYS;
    }

}
