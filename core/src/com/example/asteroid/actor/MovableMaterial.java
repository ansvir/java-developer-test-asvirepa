package com.example.asteroid.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class MovableMaterial extends Actor {

    private float weight;
    private float speed;
    private float acceleration;
    private float deceleration;

    public MovableMaterial(float weight, float speed, float acceleration, float deceleration) {
        this.weight = weight;
        this.speed = speed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

}
