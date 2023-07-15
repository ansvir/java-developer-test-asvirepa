package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.HEALTH;

public class UiStage extends Stage {

    private float defaultPadX;
    private float defaultPadY;
    private final Label lifeLabel;

    public UiStage() {
        defaultPadX = Gdx.graphics.getWidth() / 20f;
        defaultPadY = Gdx.graphics.getHeight() / 12f;
        lifeLabel = initAndGetLifeLabel();
        addActor(lifeLabel);
    }

    private Label initAndGetLifeLabel() {
        Label label = new Label("3", AssetUtil.getInstance().getDefaultSkin());
        label.setPosition(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        return label;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        defaultPadX = Gdx.graphics.getWidth() / 20f;
        defaultPadY = Gdx.graphics.getHeight() / 12f;
        lifeLabel.setPosition(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        lifeLabel.setText(String.valueOf(Cache.getInstance().getLong(HEALTH)));
    }

}
