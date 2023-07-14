package com.example.asteroid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
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
        AssetUtil.getInstance().getTiledMapRenderer().setView(ActorUtil.getInstance().getGameCamera());
        GAME_STAGE.addActor(ActorUtil.getInstance().getNewStarShip());
        Gdx.input.setInputProcessor(GAME_STAGE);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        ActorUtil.getInstance().getGameCamera().update();
        AssetUtil.getInstance().getTiledMapRenderer().render();
        GAME_STAGE.act(delta);
        GAME_STAGE.draw();
    }

    @Override
    public void resize(int width, int height) {
        Viewport viewport = ActorUtil.getInstance().getGameViewport();
        viewport.setWorldSize(width, height);
        viewport.update(width, height, true);
        AssetUtil.getInstance().updateBackgroundTileMap();
        AssetUtil.getInstance().getTiledMapRenderer()
                .setView(viewport.getCamera().projection, viewport.getScreenX(), viewport.getScreenY(), width, height);
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
        AssetUtil.getInstance().dispose();
    }

}
