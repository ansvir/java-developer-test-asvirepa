package com.example.asteroid.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.example.asteroid.util.AssetUtil;

/**
 * Actor that holds moving behaviour of actor and sprite to display.
 * Contains movement measurements, like direction or velocity.
 * Contains sprite that will be drawn.
 * Contains base methods {@link MovableSpriteActor#updatePosition(float)}, {@link MovableSpriteActor#relocateIfAtEdge()} and
 * other to manage actor behaviour.
 */
public class MovableSpriteActor extends Group {

    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private Rectangle collider;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 movement;
    protected Vector2 direction;
    protected float speed;
    private final Sprite sprite;

    public MovableSpriteActor(float maxSpeed, float acceleration, float deceleration, int spriteIndex) {
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.movement = new Vector2();
        this.direction = new Vector2();
        this.sprite = AssetUtil.getInstance().getSprites().get(spriteIndex);
        setSize(sprite.getWidth(), sprite.getHeight());
        this.collider = new Rectangle(0, 0, getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        collider.setPosition(getX(), getY());
        sprite.setPosition(getX() - sprite.getWidth() / 2f, getY() - sprite.getHeight() / 2f);
        sprite.setRotation(getRotation());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch, parentAlpha);
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

    public Sprite getSprite() {
        return sprite;
    }

    protected void updatePosition(float delta) {
        position.set(getX(), getY());
        velocity.set(direction).scl(speed);
        movement.set(velocity).scl(delta);
        position.add(movement);
        setPosition(position.x, position.y);
    }

    protected void relocateIfAtEdge() {
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
