package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.stage.GameStage;
import com.example.asteroid.util.ActorUtil;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.badlogic.gdx.Input.Keys.*;

public class StarShip extends MovableSpriteActor {

    private Vector2 lastMousePosition;
    private Vector2 lastPosition;
    private Vector2 lastDirection;

    public StarShip(float maxSpeed, float acceleration, float deceleration, int spriteIndex) {
        super(maxSpeed, acceleration, deceleration, spriteIndex);
        this.lastMousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        this.lastPosition = new Vector2();
        this.lastDirection = new Vector2();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        GameStage stage = (GameStage) getStage();
        IntSet keys = stage.getKeys();
        Vector2 mousePosition = stage.getMousePosition();
        setRotation(MathUtils.lerpAngleDeg(getRotation(), MathUtils.atan2(mousePosition.y - getY(),
                mousePosition.x - getX()) * 180.0f / MathUtils.PI - 90, 0.2f));
        if (keys.contains(Input.Keys.W)) {
            adjustSpeed(delta);
            updatePosition(delta);
        }
        if (keys.contains(Input.Keys.A)) {
            adjustSpeed(delta);
            updateLeftPosition(delta);
        }
        if (keys.contains(D)) {
            adjustSpeed(delta);
            updateRightPosition(delta);
        }
        if (!(keys.contains(Input.Keys.A) || keys.contains(D) || keys.contains(W))) {
            reduceSpeed(delta);
        }
        relocateIfAtEdge();
        handleLeftButtonPressed();
    }

    private void adjustSpeed(float delta) {
        GameStage stage = (GameStage) getStage();
        Vector2 mousePosition = stage.getMousePosition();
        lastMousePosition.set(mousePosition);
        lastPosition.set(position);
        if (Float.compare(speed, getMaxSpeed()) <= 0) {
            speed += getAcceleration() * getMaxSpeed() * delta;
        }
    }

    private void reduceSpeed(float delta) {
        lastDirection.set(direction);
        if (Float.compare(speed, 0.0f) > 0) {
            speed -= getDeceleration() * getMaxSpeed() * delta;
            updateStopPosition(delta);
        } else {
            speed = 0.0f;
        }
    }

    @Override
    protected void updatePosition(float delta) {
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

    private void handleLeftButtonPressed() {
        GameStage stage = (GameStage) getStage();
        if (stage.isTouchDown() && Gdx.input.isButtonJustPressed(LEFT)) {
            Bullet bullet = ActorUtil.getInstance().getNewBullet(stage.getTouchDownPosition(),
                    new Vector2(getX(), getY()));
            bullet.setRotation(getRotation());
            stage.addActor(bullet);
            bullet.toBack();
        }
    }

}
