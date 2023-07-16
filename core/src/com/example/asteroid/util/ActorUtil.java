package com.example.asteroid.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.*;
import com.example.asteroid.actor.ActorSprite;
import com.example.asteroid.actor.Asteroid;
import com.example.asteroid.actor.MovableMaterial;
import com.example.asteroid.actor.StarShip;

import static com.example.asteroid.AbstractConstant.BACKGROUND_TILE_HEIGHT;
import static com.example.asteroid.AbstractConstant.BACKGROUND_TILE_WIDTH;

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
        sprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        ActorSprite actorSprite = new ActorSprite(sprite);
        actorSprite.setSize(actorSprite.getWidth(), actorSprite.getHeight());
        actorSprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        MovableMaterial material = new MovableMaterial(100f, 50f, 1f);
        material.setSize(actorSprite.getWidth(), actorSprite.getHeight());
        starShip.addActor(material);
        starShip.addActor(actorSprite);
        starShip.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        starShip.setSize(sprite.getWidth(), sprite.getHeight());
        return starShip;
    }

    private Asteroid initAndGetAsteroid() {
        Asteroid asteroid = new Asteroid();
        int randomSpriteIndex = MathUtils.random(190, 200);
        Sprite sprite = AssetUtil.getInstance().getSprites().get(randomSpriteIndex);
        sprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        ActorSprite actorSprite = new ActorSprite(sprite);
        actorSprite.setSize(actorSprite.getWidth(), actorSprite.getHeight());
        actorSprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        MovableMaterial material = new MovableMaterial(40f, 20f, 1f);
        material.setSize(actorSprite.getWidth(), actorSprite.getHeight());
        asteroid.addActor(material);
        asteroid.addActor(actorSprite);
        float randomX = MathUtils.random(0.0f, Gdx.graphics.getWidth());
        float randomY = MathUtils.random(0.0f, Gdx.graphics.getHeight());
        asteroid.setPosition(randomX, randomY);
        asteroid.setSize(sprite.getWidth(), sprite.getHeight());
        return asteroid;
    }

    private Viewport initAndGetGameViewport() {
        return new FitViewport(GAME_CAMERA.viewportWidth, GAME_CAMERA.viewportHeight, GAME_CAMERA);
    }

    private OrthographicCamera initAndGetGameCamera() {
        return new OrthographicCamera(Gdx.graphics.getWidth() / (float) BACKGROUND_TILE_WIDTH,
                Gdx.graphics.getHeight() / (float) BACKGROUND_TILE_HEIGHT);
    }

    public StarShip getNewStarShip() {
        return initAndGetStarShip();
    }

    public Asteroid getNewAsteroid() {
        return initAndGetAsteroid();
    }

    public Viewport getGameViewport() {
        return GAME_VIEWPORT;
    }

    public OrthographicCamera getGameCamera() {
        return GAME_CAMERA;
    }

}
