package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends MovableSpriteActor {

    private final boolean rotateClockwise;

    public Asteroid(float maxSpeed, float acceleration, float deceleration, int spriteIndex) {
        super(maxSpeed, acceleration, deceleration, spriteIndex);
        float randomX = MathUtils.random(0.0f, Gdx.graphics.getWidth());
        float randomY = MathUtils.random(0.0f, Gdx.graphics.getHeight());
        this.rotateClockwise = MathUtils.randomBoolean();
        setPosition(randomX, randomY);
        direction.setToRandomDirection().nor();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setRotation(rotateClockwise ? getRotation() + speed * delta : getRotation() - speed * delta);
        if (Float.compare(speed, getMaxSpeed()) <= 0) {
            speed += getAcceleration() * getMaxSpeed() * delta;
        }
        updatePosition(delta);
        relocateIfAtEdge();
    }

}
