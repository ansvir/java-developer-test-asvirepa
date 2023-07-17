package com.example.asteroid.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.*;
import com.example.asteroid.actor.*;

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
        sprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        ActorSprite actorSprite = new ActorSprite(sprite);
        actorSprite.setSize(actorSprite.getWidth(), actorSprite.getHeight());
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

    public Bullet initAndGetBullet(Vector2 targetPosition, Vector2 position) {
        Bullet bullet = new Bullet(targetPosition, position);
        Sprite sprite = AssetUtil.getInstance().getSprites().get(150);
        sprite.setPosition(-sprite.getWidth() / 2f, -sprite.getHeight() / 2f);
        ActorSprite actorSprite = new ActorSprite(sprite);
        MovableMaterial material = new MovableMaterial(400f, 200f, 1f);
        material.setSize(actorSprite.getWidth(), actorSprite.getHeight());
        bullet.addActor(material);
        bullet.addActor(actorSprite);
        bullet.setSize(sprite.getWidth(), sprite.getHeight());
        return bullet;
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

    public Asteroid getNewAsteroid() {
        return initAndGetAsteroid();
    }

    public Bullet getNewBullet(Vector2 targetPosition, Vector2 position) {
        return initAndGetBullet(targetPosition, position);
    }

    public Viewport getGameViewport() {
        return GAME_VIEWPORT;
    }

    public OrthographicCamera getGameCamera() {
        return GAME_CAMERA;
    }

}
