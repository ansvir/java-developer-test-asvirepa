package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Asteroid extends Group {

    private float speed;
    private boolean rotateClockwise;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 movement;
    private Vector2 direction;

    public Asteroid() {
        float randomX = MathUtils.random(0.0f, Gdx.graphics.getWidth());
        float randomY = MathUtils.random(0.0f, Gdx.graphics.getHeight());
        this.rotateClockwise = MathUtils.randomBoolean();
        this.position = new Vector2(randomX, randomY);
        this.velocity = new Vector2();
        this.movement = new Vector2();
        this.direction = new Vector2();
        direction.setToRandomDirection().nor();
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());
        position.add(movement);
        setPosition(position.x, position.y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setRotation(rotateClockwise ? getRotation() + speed * delta : getRotation() - speed * delta);
        MovableMaterial asteroidPhysics = (MovableMaterial) getChildren().get(0);
        if (Float.compare(speed, asteroidPhysics.getMaxSpeed()) <= 0) {
            speed = speed + asteroidPhysics.getAcceleration() * asteroidPhysics.getMaxSpeed() * delta;
        }
        updatePosition(delta);
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

    private void updatePosition(float delta) {
        position.set(getX(), getY());
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(delta);
        position.add(movement);
        setPosition(position.x, position.y);
    }

}
