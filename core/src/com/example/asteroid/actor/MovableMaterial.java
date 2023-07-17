package com.example.asteroid.actor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MovableMaterial extends Actor {

    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private Rectangle collider;

    public MovableMaterial(float maxSpeed, float acceleration, float deceleration) {
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.collider = new Rectangle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        collider.setPosition(getX(), getY());
        collider.setSize(getWidth(), getHeight());
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

}
