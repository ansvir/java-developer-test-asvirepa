package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class UiStage extends Stage {

    protected static final float PAD_X = 20f;
    protected static final float PAD_Y = 12f;
    protected float defaultPadX;
    protected float defaultPadY;

    @Override
    public void act(float delta) {
        super.act(delta);
        defaultPadX = Gdx.graphics.getWidth() / PAD_X;
        defaultPadY = Gdx.graphics.getHeight() / PAD_Y;
    }

}
