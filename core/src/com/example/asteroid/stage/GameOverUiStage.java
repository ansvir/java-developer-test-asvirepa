package com.example.asteroid.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.example.asteroid.screen.GameScreen;
import com.example.asteroid.util.AssetUtil;

import static com.badlogic.gdx.utils.Align.center;

/**
 * {@link UiStage} that is responsible for process events and display {@link com.example.asteroid.screen.GameOverScreen}
 */
public class GameOverUiStage extends UiStage {

    private final Game GAME;

    public GameOverUiStage(Game game) {
        this.GAME = game;
        addActor(initAndGetGameOverLabel());
        addActor(initAndGetRestartButton());
    }

    private InputListener initAndGetRestartButtonInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                GAME.setScreen(new GameScreen(GAME));
                return true;
            }
        };
    }

    private Label initAndGetGameOverLabel() {
        Label label = new Label("GAME OVER", AssetUtil.getInstance().getDefaultSkin());
        label.setOrigin(center);
        label.setPosition(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f + Gdx.graphics.getHeight() / 4f - label.getHeight() / 2f);
        label.setFontScale(1.5f);
        return label;
    }

    private TextButton initAndGetRestartButton() {
        TextButton startButton = new TextButton("RESTART", AssetUtil.getInstance().getDefaultSkin());
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - Gdx.graphics.getHeight() / 4f - startButton.getHeight() / 2f);
        startButton.setOrigin(center);
        startButton.addListener(initAndGetRestartButtonInputListener());
        return startButton;
    }

}
