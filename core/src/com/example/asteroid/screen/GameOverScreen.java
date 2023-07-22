package com.example.asteroid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.asteroid.stage.GameOverUiStage;

public class GameOverScreen implements Screen {

    private final Game GAME;
    private final Stage GAME_OVER_UI_STAGE;

    public GameOverScreen(Game game) {
        this.GAME = game;
        this.GAME_OVER_UI_STAGE = new GameOverUiStage(GAME);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(GAME_OVER_UI_STAGE);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.RED);
        GAME_OVER_UI_STAGE.act(delta);
        GAME_OVER_UI_STAGE.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
