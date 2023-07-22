package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.HEALTH;
import static com.example.asteroid.AbstractConstant.SCORE;

public class UiStage extends Stage {

    private static final float PAD_X = 20f;
    private static final float PAD_Y = 12f;

    private float defaultPadX;
    private float defaultPadY;
    private final Label lifeLabel;
    private final Label scoreLabel;

    public UiStage() {
        defaultPadX = Gdx.graphics.getWidth() / PAD_X;
        defaultPadY = Gdx.graphics.getHeight() / PAD_Y;
        lifeLabel = initAndGetLabel("3", new Vector2(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY));
        scoreLabel = initAndGetLabel("0", new Vector2(defaultPadX, Gdx.graphics.getHeight() - defaultPadY));
        addActor(lifeLabel);
        addActor(scoreLabel);
    }

    private Label initAndGetLabel(String text, Vector2 position) {
        Label label = new Label(text, AssetUtil.getInstance().getDefaultSkin());
        label.setPosition(position.x, position.y);
        return label;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        defaultPadX = Gdx.graphics.getWidth() / PAD_X;
        defaultPadY = Gdx.graphics.getHeight() / PAD_Y;
        lifeLabel.setPosition(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        lifeLabel.setText(String.valueOf(Cache.getInstance().getLong(HEALTH)));
        scoreLabel.setPosition(defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        scoreLabel.setText(String.valueOf(Cache.getInstance().getLong(SCORE)));
    }

}
