package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.stage.GameStage;
import com.example.asteroid.util.ActorUtil;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.W;

public class StarShip extends Group {

    private float speed;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 movement;
    private Vector2 direction;
    private Vector2 lastMousePosition;
    private Vector2 lastPosition;
    private Vector2 lastDirection;
    private Vector2 lastShootPosition;
    private boolean isShot;

    public StarShip() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.movement = new Vector2();
        this.direction = new Vector2();
        this.lastMousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        this.lastPosition = new Vector2();
        this.lastDirection = new Vector2();
        this.lastShootPosition = new Vector2();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        GameStage stage = (GameStage) getStage();
        IntSet keys = stage.getKeys();
        Vector2 mousePosition = stage.getMousePosition();
        MovableMaterial starShipPhysics = (MovableMaterial) getChildren().get(0);
        setRotation(MathUtils.lerpAngleDeg(getRotation(), MathUtils.atan2(mousePosition.y - getY(),
                mousePosition.x - getX()) * 180.0f / MathUtils.PI - 90, 0.2f));
        if (keys.contains(Input.Keys.W)) {
            lastMousePosition.set(mousePosition);
            lastPosition.set(position);
            if (Float.compare(speed, starShipPhysics.getMaxSpeed()) <= 0) {
                speed = speed + starShipPhysics.getAcceleration() * starShipPhysics.getMaxSpeed() * delta;
            }
            updatePosition(delta);
        }
        if (keys.contains(Input.Keys.A)) {
            lastMousePosition.set(mousePosition);
            lastPosition.set(position);
            if (Float.compare(speed, starShipPhysics.getMaxSpeed()) <= 0) {
                speed = speed + starShipPhysics.getAcceleration() * starShipPhysics.getMaxSpeed() * delta;
            }
            updateLeftPosition(delta);
        }
        if (keys.contains(D)) {
            lastMousePosition.set(mousePosition);
            lastPosition.set(position);
            if (Float.compare(speed, starShipPhysics.getMaxSpeed()) <= 0) {
                speed = speed + starShipPhysics.getAcceleration() * starShipPhysics.getMaxSpeed() * delta;
            }
            updateRightPosition(delta);
        }
        if (!(keys.contains(Input.Keys.A) || keys.contains(D) || keys.contains(W))) {
            lastDirection.set(direction);
            if (Float.compare(speed, 0.0f) > 0) {
                speed -= starShipPhysics.getDeceleration() * starShipPhysics.getMaxSpeed() * delta;
                updateStopPosition(delta);
            } else {
                speed = 0.0f;
            }
        }
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
        if (stage.isTouchDown() && !isShot) {
            isShot = true;
            Bullet bullet = ActorUtil.getInstance().getNewBullet(stage.getTouchDownPosition(), new Vector2(getX(), getY()));
            bullet.setRotation(getRotation());
            stage.addActor(bullet);
            bullet.toBack();
        } else {
            isShot = false;
        }
    }

    private void updatePosition(float delta) {
        position.set(getX(), getY());
        direction.set(lastMousePosition).sub(lastPosition).nor();
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(delta);
        if (lastPosition.dst2(lastMousePosition) > movement.len2()) {
            position.add(movement);
        }
        setPosition(position.x, position.y);
    }

    private void updateLeftPosition(float delta) {
        position.set(getX(), getY());
        Vector2 leftPosition = lastPosition.cpy().rotateAroundDeg(lastMousePosition, 90);
        direction.set(lastMousePosition).sub(leftPosition).nor();
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(delta);
        if (lastPosition.dst2(lastMousePosition) > movement.len2()) {
            position.add(movement);
        }
        setPosition(position.x, position.y);
    }

    private void updateRightPosition(float delta) {
        position.set(getX(), getY());
        Vector2 rightPosition = lastPosition.cpy().rotateAroundDeg(lastMousePosition, -90);
        direction.set(lastMousePosition).sub(rightPosition).nor();
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(delta);
        if (lastPosition.dst2(lastMousePosition) > movement.len2()) {
            position.add(movement);
        }
        setPosition(position.x, position.y);
    }

    private void updateStopPosition(float delta) {
        position.set(getX(), getY());
        velocity.set(lastDirection).scl(speed);
        movement.set(velocity).scl(delta);
        if (lastPosition.dst2(lastMousePosition) > movement.len2()) {
            position.add(movement);
        }
        setPosition(position.x, position.y);
    }

}
