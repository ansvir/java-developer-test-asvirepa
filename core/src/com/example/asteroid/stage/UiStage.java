package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.HEALTH;
import static com.example.asteroid.AbstractConstant.SCORE;

public class UiStage extends Stage {

    private float defaultPadX;
    private float defaultPadY;
    private final Label lifeLabel;
    private final Label scoreLabel;

    public UiStage() {
        defaultPadX = Gdx.graphics.getWidth() / 20f;
        defaultPadY = Gdx.graphics.getHeight() / 12f;
        lifeLabel = initAndGetLifeLabel();
        scoreLabel = initAndGetScoreLabel();
        addActor(lifeLabel);
        addActor(scoreLabel);
    }

    private Label initAndGetLifeLabel() {
        Label label = new Label("3", AssetUtil.getInstance().getDefaultSkin());
        label.setPosition(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        return label;
    }

    private Label initAndGetScoreLabel() {
        Label label = new Label("0", AssetUtil.getInstance().getDefaultSkin());
        label.setPosition(defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        return label;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        defaultPadX = Gdx.graphics.getWidth() / 20f;
        defaultPadY = Gdx.graphics.getHeight() / 12f;
        lifeLabel.setPosition(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        lifeLabel.setText(String.valueOf(Cache.getInstance().getLong(HEALTH)));
        scoreLabel.setPosition(defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        scoreLabel.setText(String.valueOf(Cache.getInstance().getLong(SCORE)));
    }

}
