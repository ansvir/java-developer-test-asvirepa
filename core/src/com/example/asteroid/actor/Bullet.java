package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.example.asteroid.stage.GameStage;

public class Bullet extends Group {

    private float speed;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 movement;
    private Vector2 direction;

    public Bullet(Vector2 targetPosition) {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.movement = new Vector2();
        this.direction = new Vector2();
        Vector2 targetAngle = new Vector2(MathUtils.cos(targetPosition.angleRad()), MathUtils.sin(targetPosition.angleRad()));
        direction.set(targetPosition).nor();
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());
        position.add(movement);
        setPosition(position.x, position.y);
//        Vector2 positionDiff = targetPosition.cpy().sub(getX(), getY());
//        direction.set(positionDiff).nor();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        MovableMaterial bulletPhysics = (MovableMaterial) getChildren().get(0);
        if (Float.compare(speed, bulletPhysics.getMaxSpeed()) <= 0) {
            speed = speed + bulletPhysics.getAcceleration() * bulletPhysics.getMaxSpeed() * delta;
        }
        updatePosition(delta);
        if (getX() > Gdx.graphics.getWidth() || getX() < 0 || getY() > Gdx.graphics.getHeight() || getY() < 0) {
            remove();
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
