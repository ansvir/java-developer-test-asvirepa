package com.example.asteroid.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.asteroid.actor.Asteroid;
import com.example.asteroid.actor.Bullet;
import com.example.asteroid.actor.StarShip;

public final class ActorUtil {

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

    private Viewport initAndGetGameViewport() {
        return new FillViewport(GAME_CAMERA.viewportWidth, GAME_CAMERA.viewportHeight, GAME_CAMERA);
    }

    private OrthographicCamera initAndGetGameCamera() {
        return new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public StarShip getNewStarShip() {
        StarShip starShip = new StarShip( 100f, 50f, 1f, 70);
        starShip.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        return starShip;
    }

    public Asteroid getNewAsteroid() {
        return new Asteroid(40f, 20f, 1f, MathUtils.random(190, 200));
    }

    public Bullet getNewBullet(Vector2 targetPosition, Vector2 position) {
        return new Bullet(targetPosition, position, 400f, 200f, 1f, 150);
    }

    public Viewport getGameViewport() {
        return GAME_VIEWPORT;
    }

    public OrthographicCamera getGameCamera() {
        return GAME_CAMERA;
    }

}
