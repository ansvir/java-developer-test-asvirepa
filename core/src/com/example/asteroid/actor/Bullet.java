package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends MovableSpriteActor {

    public Bullet(Vector2 targetPosition, Vector2 position, float maxSpeed, float acceleration, float deceleration, int spriteIndex) {
        super(maxSpeed, acceleration, deceleration, spriteIndex);
        setPosition(position.x, position.y);
        direction.set(targetPosition).sub(position).nor();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (Float.compare(speed, getMaxSpeed()) <= 0) {
            speed += getAcceleration() * getMaxSpeed() * delta;
        }
        updatePosition(delta);
        if (getX() > Gdx.graphics.getWidth() || getX() < 0 || getY() > Gdx.graphics.getHeight() || getY() < 0) {
            remove();
        }
    }

}
