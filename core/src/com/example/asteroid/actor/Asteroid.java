package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.W;

public class Asteroid extends Group {

    private float speed;
    private Vector2 velocity;
    private boolean rotateClockwise;

    public Asteroid() {
        float randomX = MathUtils.random(0.0f, Gdx.graphics.getWidth());
        float randomY = MathUtils.random(0.0f, Gdx.graphics.getHeight());
        this.velocity = new Vector2(randomX, randomY);
        this.rotateClockwise = MathUtils.randomBoolean();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setRotation(rotateClockwise ? getRotation() + speed : getRotation() - speed);
        MovableMaterial asteroidPhysics = (MovableMaterial) getChildren().get(0);
        velocity.set(velocity.x + 1, velocity.y + 1);
        if (Float.compare(speed, asteroidPhysics.getMaxSpeed()) <= 0) {
            speed = speed + asteroidPhysics.getAcceleration() * asteroidPhysics.getMaxSpeed() * delta;
        }
        setPosition(getX() + (getX() < velocity.x ? speed : -speed),
                getY() + (getY() < velocity.y ? speed : -speed));
        if (getX() > Gdx.graphics.getWidth()) {
            setX(0);
        } else if (getX() < 0) {
            setX(Gdx.graphics.getWidth());
        }
        if (getY() > Gdx.graphics.getHeight()) {
            setY(0);
        } else if (getY() < 0) {
            setY(Gdx.graphics.getHeight());
        }
    }

}
