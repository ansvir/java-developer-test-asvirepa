package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.actor.Asteroid;
import com.example.asteroid.actor.Bullet;
import com.example.asteroid.actor.MovableMaterial;
import com.example.asteroid.actor.StarShip;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

import java.util.concurrent.atomic.AtomicReference;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.example.asteroid.AbstractConstant.HEALTH;
import static com.example.asteroid.AbstractConstant.SCORE;

public class GameStage extends Stage {

    private final IntSet KEYS;
    private final Vector2 mousePosition;
    private final Vector2 touchDownPosition;
    private boolean isTouchDown;
    private int maxAsteroids;

    public GameStage() {
        super(ActorUtil.getInstance().getGameViewport(), AssetUtil.getInstance().getSpriteBatch());
        this.KEYS = new IntSet();
        this.mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        this.touchDownPosition = new Vector2();
        this.maxAsteroids = 3;
        for (int i = 0; i < maxAsteroids; i++) {
            addActor(ActorUtil.getInstance().getNewAsteroid());
        }
        addListener(initAndGetUserInputListener());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        AtomicReference<StarShip> starShip = new AtomicReference<>();
        getActors().forEach(a -> {
            if (a instanceof StarShip) {
                starShip.set(((StarShip) a));
            }
        });
        Array<Actor> actors = getActors();
        boolean isStarShipDestroyed = false;
        for (int i = 0; i < actors.size; i++) {
            if (!(actors.get(i) instanceof StarShip)) {
                if (actors.get(i) instanceof Asteroid) {
                    MovableMaterial mm1 = ((MovableMaterial) starShip.get().getChildren().get(1));
                    MovableMaterial mm2 = ((MovableMaterial) ((Asteroid) actors.get(i)).getChildren().get(1));
                    int finalI = i;
                    if (mm1.getCollider().overlaps(mm2.getCollider())) {
                        actors.get(i).remove();
                        isStarShipDestroyed = true;
                        long health = Cache.getInstance().getLong(HEALTH) - 1L;
                        if (health <= 0L) {
                            restartGame();
                        } else {
                            Cache.getInstance().setLong(HEALTH, health);
                        }
                        addActor(ActorUtil.getInstance().getNewAsteroid());
                    }
                    getActors().iterator().forEach(aa -> {
                        if (aa instanceof Bullet) {
                            MovableMaterial mm3 = ((MovableMaterial) ((Bullet) aa).getChildren().get(1));
                            if (mm3.getCollider().overlaps(mm2.getCollider())) {
                                if (actors.size > finalI) {
                                    actors.get(finalI).remove();
                                    aa.remove();
                                    addActor(ActorUtil.getInstance().getNewAsteroid());
                                    Long score = Cache.getInstance().getLong(SCORE);
                                    Cache.getInstance().setLong(SCORE, score + 500L);
                                }
                            }
                        }
                    });
                }
            }
        }
        if (isStarShipDestroyed) {
            starShip.get().remove();
            addActor(ActorUtil.getInstance().getNewStarShip());
        }
        isStarShipDestroyed = false;
    }

    private void restartGame() {
        Cache.getInstance().setLong(HEALTH, 3L);
        Cache.getInstance().setLong(SCORE, 0L);
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
