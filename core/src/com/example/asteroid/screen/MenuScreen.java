package com.example.asteroid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.asteroid.stage.MenuUiStage;

public class MenuScreen implements Screen {

    private final Game GAME;
    private final Stage MENU_UI_STAGE;

    public MenuScreen(Game game) {
        this.GAME = game;
        this.MENU_UI_STAGE = new MenuUiStage(GAME);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(MENU_UI_STAGE);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        MENU_UI_STAGE.act(delta);
        MENU_UI_STAGE.draw();
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
