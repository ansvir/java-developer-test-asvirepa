package com.example.asteroid.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.asteroid.stage.GameStage;
import com.example.asteroid.stage.GameUiStage;
import com.example.asteroid.storage.Cache;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

import static com.example.asteroid.AbstractConstant.HEALTH;
import static com.example.asteroid.AbstractConstant.SCORE;

public class GameScreen implements Screen {

    private final Game GAME;
    private final Stage GAME_STAGE;
    private final Stage UI_STAGE;

    public GameScreen(Game game) {
        initCache();
        this.GAME = game;
        this.GAME_STAGE = new GameStage(GAME);
        this.UI_STAGE = new GameUiStage();
    }

    @Override
    public void show() {
        AssetUtil.getInstance().getTiledMapRenderer().setView(ActorUtil.getInstance().getGameCamera());
        Gdx.input.setInputProcessor(GAME_STAGE);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        ActorUtil.getInstance().getGameCamera().update();
        AssetUtil.getInstance().getTiledMapRenderer().render();
        GAME_STAGE.act(delta);
        GAME_STAGE.draw();
        UI_STAGE.act(delta);
        UI_STAGE.draw();
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

    }

    private void initCache() {
        Cache cache = Cache.getInstance();
        cache.setLong(HEALTH, 3L);
        cache.setLong(SCORE, 0L);
    }

}
