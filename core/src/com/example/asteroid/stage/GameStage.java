package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

public class GameStage extends Stage {

    private final IntSet KEYS;
    private final Vector2 mousePosition;
    private int maxAsteroids;

    public GameStage() {
        super(ActorUtil.getInstance().getGameViewport(), AssetUtil.getInstance().getSpriteBatch());
        this.KEYS = new IntSet();
        this.mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        this.maxAsteroids = 3;
        for (int i = 0; i < maxAsteroids; i++) {
            addActor(ActorUtil.getInstance().getNewAsteroid());
        }
        addListener(initAndGetUserInputListener());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                super.mouseMoved(event, x, y);
                mousePosition.set(new Vector2(x, y));
                return false;
            }

        };
    }

    public IntSet getKeys() {
        return KEYS;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }

}
