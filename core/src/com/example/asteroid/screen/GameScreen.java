package com.example.asteroid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.asteroid.stage.GameStage;
import com.example.asteroid.stage.UiStage;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.*;

public class GameScreen implements Screen {

    private final Game GAME;
    private final Stage GAME_STAGE;
    private final Stage UI_STAGE;

    public GameScreen(Game game) {
        initCache();
        this.GAME = game;
        this.GAME_STAGE = new GameStage();
        this.UI_STAGE = new UiStage();
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
        AssetUtil.getInstance().getTiledMapRenderer().render();
        GAME_STAGE.act(delta);
        GAME_STAGE.draw();
        UI_STAGE.act(delta);
        UI_STAGE.draw();
    }

    @Override
    public void resize(int width, int height) {
        Viewport viewport = ActorUtil.getInstance().getGameViewport();
//        viewport.setWorldSize(width, height);
//        viewport.update(width, height, true);
        AssetUtil.getInstance().updateBackgroundTileMap();
        ((OrthographicCamera) viewport.getCamera()).setToOrtho(false, width / (float) BACKGROUND_TILE_WIDTH,
                height / (float) BACKGROUND_TILE_HEIGHT);
        viewport.getCamera().update();
//        AssetUtil.getInstance().getTiledMapRenderer()
//                .setView(viewport.getCamera().combined, viewport.getScreenX(), viewport.getScreenY(), width, height);
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

    private void initCache() {
        Cache cache = Cache.getInstance();
        cache.setLong(HEALTH, 3L);
    }

}
