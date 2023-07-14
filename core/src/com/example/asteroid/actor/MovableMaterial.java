package com.example.asteroid.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class MovableMaterial extends Actor {

    private float weight;
    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    public MovableMaterial(float weight, float maxSpeed, float acceleration, float deceleration) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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

}
