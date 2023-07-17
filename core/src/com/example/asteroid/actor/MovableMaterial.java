package com.example.asteroid.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MovableMaterial extends Actor {

    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private Rectangle collider;

    public MovableMaterial(Vector2 size, float maxSpeed, float acceleration, float deceleration) {
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.collider = new Rectangle(0, 0, size.x, size.y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        collider.setPosition(getParent().getX(), getParent().getY());
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
