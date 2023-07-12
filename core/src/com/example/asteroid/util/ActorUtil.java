package com.example.asteroid.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.asteroid.actor.ActorSprite;
import com.example.asteroid.actor.MovableMaterial;
import com.example.asteroid.actor.StarShip;

public class ActorUtil {

    private static ActorUtil instance;

    private final Viewport GAME_VIEWPORT;
    private final OrthographicCamera GAME_CAMERA;

    public ActorUtil() {
        this.GAME_VIEWPORT = initAndGetGameViewport();
        this.GAME_CAMERA = initAndGetGameCamera();
    }

    public static ActorUtil getInstance() {
        if (instance == null) {
            instance = new ActorUtil();
        }
        return instance;
    }

    private StarShip initAndGetStarShip() {
        StarShip starShip = new StarShip();
        starShip.addActor(new MovableMaterial(10000f, 1000f, 100f, 50f));
        starShip.addActor(new ActorSprite(AssetUtil.getInstance().getSprites().get(20)));
        return starShip;
    }

    private Viewport initAndGetGameViewport() {
        return new ScreenViewport();
    }

    private OrthographicCamera initAndGetGameCamera() {
        return new OrthographicCamera(GAME_VIEWPORT.getScreenWidth(), GAME_VIEWPORT.getScreenHeight());
    }

    public StarShip getNewStarShip() {
        return initAndGetStarShip();
    }

    public Viewport getGameViewport() {
        return GAME_VIEWPORT;
    }

    public OrthographicCamera getGameCamera() {
        return GAME_CAMERA;
    }

}
