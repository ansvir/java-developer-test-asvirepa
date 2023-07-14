package com.example.asteroid.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.*;
import com.example.asteroid.actor.ActorSprite;
import com.example.asteroid.actor.MovableMaterial;
import com.example.asteroid.actor.StarShip;

import static com.badlogic.gdx.utils.Align.center;

public class ActorUtil {

    private static ActorUtil instance;

    private final Viewport GAME_VIEWPORT;
    private final OrthographicCamera GAME_CAMERA;

    public ActorUtil() {
        this.GAME_CAMERA = initAndGetGameCamera();
        this.GAME_VIEWPORT = initAndGetGameViewport();
    }

    public static ActorUtil getInstance() {
        if (instance == null) {
            instance = new ActorUtil();
        }
        return instance;
    }

    private StarShip initAndGetStarShip() {
        StarShip starShip = new StarShip();
        Sprite sprite = AssetUtil.getInstance().getSprites().get(70);
        sprite.setOriginCenter();
        sprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        ActorSprite actorSprite = new ActorSprite(sprite);
        MovableMaterial material = new MovableMaterial(10000f, 2f, 20f, 1f);
        material.setOrigin(center);
        starShip.addActor(material);
        starShip.addActor(actorSprite);
        starShip.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        starShip.setSize(actorSprite.getWidth(), actorSprite.getHeight());
        starShip.setOrigin(center);
        return starShip;
    }

    private Viewport initAndGetGameViewport() {
        return new FillViewport(GAME_CAMERA.viewportWidth, GAME_CAMERA.viewportHeight, GAME_CAMERA);
    }

    private OrthographicCamera initAndGetGameCamera() {
        return new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
