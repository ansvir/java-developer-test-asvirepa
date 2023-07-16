package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.HEALTH;

public class UiStage extends Stage {

    private float defaultMarginX;
    private float defaultMarginY;
    private final Label lifeLabel;

    public UiStage() {
        defaultMarginX = Gdx.graphics.getWidth() / 20f;
        defaultMarginY = Gdx.graphics.getHeight() / 12f;
        lifeLabel = initAndGetLifeLabel();
        addActor(lifeLabel);
    }

    private Label initAndGetLifeLabel() {
        Label label = new Label("3", AssetUtil.getInstance().getDefaultSkin());
        label.setPosition(Gdx.graphics.getWidth() - defaultMarginX, Gdx.graphics.getHeight() - defaultMarginY);
        return label;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        defaultMarginX = Gdx.graphics.getWidth() / 20f;
        defaultMarginY = Gdx.graphics.getHeight() / 12f;
        lifeLabel.setPosition(Gdx.graphics.getWidth() - defaultMarginX, Gdx.graphics.getHeight() - defaultMarginY);
        lifeLabel.setText(String.valueOf(Cache.getInstance().getLong(HEALTH)));
    }

}
