package com.example.asteroid.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.actor.Asteroid;
import com.example.asteroid.actor.Bullet;
import com.example.asteroid.actor.MovableSpriteActor;
import com.example.asteroid.actor.StarShip;
import com.example.asteroid.screen.GameOverScreen;
import com.example.asteroid.screen.GameScreen;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;
import com.example.asteroid.util.ServiceUtil;

import java.util.Optional;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.example.asteroid.AbstractConstant.HEALTH;
import static com.example.asteroid.AbstractConstant.SCORE;

public class GameStage extends Stage {

    private static final int COMPLEXITY_LEVEL = 1500;

    private final Game GAME;
    private final IntSet KEYS;
    private final Vector2 mousePosition;
    private final Vector2 touchDownPosition;
    private boolean isTouchDown;
    private int maxAsteroids;

    public GameStage(Game game) {
        super(ActorUtil.getInstance().getGameViewport(), AssetUtil.getInstance().getSpriteBatch());
        this.GAME = game;
        this.KEYS = new IntSet();
        this.mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        this.touchDownPosition = new Vector2();
        this.maxAsteroids = 3;
        StarShip starShip = ActorUtil.getInstance().getNewStarShip();
        addActor(starShip);
        for (int i = 0; i < maxAsteroids; i++) {
            addActor(ActorUtil.getInstance().getNewAsteroid(new Vector2(starShip.getX(), starShip.getY()),
                    new Vector2(starShip.getWidth(), starShip.getHeight())));
        }
        addListener(initAndGetUserInputListener());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Optional<StarShip> starShip = ServiceUtil.findActorOnStage(this, StarShip.class);
        Array<Asteroid> asteroids = ServiceUtil.findAllActorsOnStage(this, Asteroid.class);
        Array<Bullet> bullets = ServiceUtil.findAllActorsOnStage(this, Bullet.class);
        starShip.filter(s -> {
            Array<Asteroid> destroyedAsteroids = new Array<>();
            Array<Bullet> destroyedBullets = new Array<>();
            Optional<StarShip> destroyedStarShip = Optional.empty();
            for (Asteroid asteroid : asteroids) {
                if (tryStarShipCollision(s, asteroid)) {
                    destroyedAsteroids.add(asteroid);
                    destroyedStarShip = Optional.of(s);
                }
                for (Bullet bullet : bullets) {
                    if (tryBulletCollision(bullet, asteroid)) {
                        destroyedAsteroids.add(asteroid);
                        destroyedBullets.add(bullet);
                    }
                }
            }
            destroyedAsteroids.forEach(da -> {
                da.remove();
                addActor(ActorUtil.getInstance().getNewAsteroid(new Vector2(s.getX(), s.getY()),
                        new Vector2(s.getWidth(), s.getHeight())));
            });
            destroyedBullets.forEach(Actor::remove);
            return destroyedStarShip.isPresent();
        }).ifPresent(s -> {
            s.remove();
            addActor(ActorUtil.getInstance().getNewStarShip());
        });
        // for future commits
//        tryIncreaseComplexity();
//        for (int i = 0; i < maxAsteroids - asteroids.size; i++) {
//            addActor(ActorUtil.getInstance().getNewAsteroid());
//        }
    }

    private void restartGame() {
        GAME.setScreen(new GameOverScreen(GAME));
    }

    private boolean tryStarShipCollision(MovableSpriteActor a1, MovableSpriteActor a2) {
        if (a1.getCollider().overlaps(a2.getCollider())) {
            long health = Cache.getInstance().getLong(HEALTH) - 1L;
            if (health <= 0L) {
                restartGame();
            } else {
                Cache.getInstance().setLong(HEALTH, health);
            }
            return true;
        }
        return false;
    }

    private boolean tryBulletCollision(MovableSpriteActor a1, MovableSpriteActor a2) {
        if (a1.getCollider().overlaps(a2.getCollider())) {
            Long score = Cache.getInstance().getLong(SCORE);
            Cache.getInstance().setLong(SCORE, score + 500L);
            return true;
        }
        return false;
    }

    private void tryIncreaseComplexity() {
        maxAsteroids += Cache.getInstance().getLong(SCORE) / COMPLEXITY_LEVEL;
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
                return true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                touchDownPosition.set(new Vector2(x, y));
                if (button == LEFT) {
                    isTouchDown = true;
                    return true;
                }
                return false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                touchDownPosition.set(new Vector2(x, y));
                mousePosition.set(new Vector2(x, y));
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (button == LEFT) {
                    isTouchDown = false;
                }
            }
        };
    }

    public IntSet getKeys() {
        return KEYS;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }

    public Vector2 getTouchDownPosition() {
        return touchDownPosition;
    }

    public boolean isTouchDown() {
        return isTouchDown;
    }

}
