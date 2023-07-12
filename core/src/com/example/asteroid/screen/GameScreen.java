package com.example.asteroid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.asteroid.stage.GameStage;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

public class GameScreen implements Screen {

    private final Game GAME;
    private final Stage GAME_STAGE;

    public GameScreen(Game game) {
        this.GAME = game;
        this.GAME_STAGE = new GameStage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(GAME_STAGE);
    }

    @Override
    public void render(float delta) {

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
