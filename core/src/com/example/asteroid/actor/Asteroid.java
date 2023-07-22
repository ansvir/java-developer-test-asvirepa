package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Game enemy that moves in spontaneous direction and serves as a obstacle for player. Has speed and direction, after
 * colliding with it player lose his health
 */
public class Asteroid extends MovableSpriteActor {

    private final boolean rotateClockwise;

    public Asteroid(Vector2 starShipCoordinates, Vector2 starShipSize, float maxSpeed, float acceleration, float deceleration, int spriteIndex) {
        super(maxSpeed, acceleration, deceleration, spriteIndex);
        this.rotateClockwise = MathUtils.randomBoolean();
        Vector2 coordinates = getSpawnCoordinates(starShipCoordinates, starShipSize);
        setPosition(coordinates.x, coordinates.y);
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

    private Vector2 getSpawnCoordinates(Vector2 starShipCoordinates, Vector2 starShipSize) {
        float randomX = MathUtils.random(0.0f, Gdx.graphics.getWidth());
        float randomY = MathUtils.random(0.0f, Gdx.graphics.getHeight());
        Rectangle starShipCollider = new Rectangle(starShipCoordinates.x, starShipCoordinates.y, starShipSize.x, starShipSize.y);
        if (!getCollider().overlaps(starShipCollider)) {
            return new Vector2(randomX, randomY);
        }
        return getSpawnCoordinates(starShipCoordinates, starShipSize);
    }

}
