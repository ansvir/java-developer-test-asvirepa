package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.stage.GameStage;

import javax.swing.*;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.W;

public class StarShip extends Group {

    private float speed;

    @Override
    public void act(float delta) {
        super.act(delta);
        IntSet keys = ((GameStage) getStage()).getKeys();
        Vector2 mousePosition = ((GameStage) getStage()).getMousePosition();
        setRotation(MathUtils.atan2(mousePosition.y - getY(), mousePosition.x - getX()) * 180.0f / MathUtils.PI - 90);
        MovableMaterial starShipPhysics = (MovableMaterial) getChildren().get(0);
        if (keys.contains(Input.Keys.W)) {
            if (Float.compare(speed, starShipPhysics.getMaxSpeed()) <= 0) {
                speed = speed + starShipPhysics.getAcceleration() * starShipPhysics.getMaxSpeed() * delta;
            }
            setPosition(getX() + (getX() < mousePosition.x ? speed : -speed),
                    getY() + (getY() < mousePosition.y ? speed : -speed));
        }
        if (keys.contains(Input.Keys.A)) {
            setX(getX() - speed);
        }
        if (keys.contains(D)) {
            setX(getX() + speed);
        }
        if (!(keys.contains(Input.Keys.A) || keys.contains(D) || keys.contains(W))) {
            if (Float.compare(speed, 0.0f) > 0) {
                speed -= starShipPhysics.getDeceleration() * starShipPhysics.getMaxSpeed() * delta;
                setPosition(getX() + (getX() < mousePosition.x ? speed : -speed),
                        getY() + (getY() < mousePosition.y ? speed : -speed));
            } else {
                speed = 0.0f;
            }
        }
        System.out.println(speed);
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
