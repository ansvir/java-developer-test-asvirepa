package com.example.asteroid.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.HEALTH;
import static com.example.asteroid.AbstractConstant.SCORE;

/**
 * {@link UiStage} that is responsible for processing game actions
 */
public class GameUiStage extends UiStage {

    private final Label LIFE_LABEL;
    private final Label SCORE_LABEL;

    public GameUiStage() {
        LIFE_LABEL = initAndGetLabel("3", new Vector2(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY));
        SCORE_LABEL = initAndGetLabel("0", new Vector2(defaultPadX, Gdx.graphics.getHeight() - defaultPadY));
        addActor(LIFE_LABEL);
        addActor(SCORE_LABEL);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateUiElements();
    }

    private void updateUiElements() {
        LIFE_LABEL.setPosition(Gdx.graphics.getWidth() - defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        LIFE_LABEL.setText(String.valueOf(Cache.getInstance().getLong(HEALTH)));
        SCORE_LABEL.setPosition(defaultPadX, Gdx.graphics.getHeight() - defaultPadY);
        SCORE_LABEL.setText(String.valueOf(Cache.getInstance().getLong(SCORE)));
    }

    private Label initAndGetLabel(String text, Vector2 position) {
        Label label = new Label(text, AssetUtil.getInstance().getDefaultSkin());
        label.setPosition(position.x, position.y);
        return label;
    }

}
