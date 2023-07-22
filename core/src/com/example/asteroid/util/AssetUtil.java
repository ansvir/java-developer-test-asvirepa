package com.example.asteroid.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import static com.example.asteroid.AbstractConstant.BACKGROUND_TILE_HEIGHT;
import static com.example.asteroid.AbstractConstant.BACKGROUND_TILE_WIDTH;

/**
 * Class that defines basic assets operations, such as initializing and provides access to these assets
 * Build as singleton to avoid assets duplication and memory leak
 */
public final class AssetUtil implements Disposable {

    private static AssetUtil instance;

    private final Array<Sprite> SPRITES;
    private final TextureAtlas TEXTURE_ATLAS;
    private final SpriteBatch SPRITE_BATCH;
    private TiledMapRenderer TILED_MAP_RENDERER;
    private TiledMapTileLayer BACKGROUND_LAYER;
    private TiledMapTileSet BACKGROUND_TILESET;
    private TiledMap BACKGROUND_TILED_MAP;
    private final Texture BACKGROUND_TILE_TEXTURE;
    private final Skin DEFAULT_SKIN;

    private AssetUtil() {
        TEXTURE_ATLAS = getTextureAtlas();
        SPRITES = initAndGetSprites();
        SPRITE_BATCH = initAndGetSpriteBatch();
        BACKGROUND_TILE_TEXTURE = initAndGetBackgroundTileTexture();
        BACKGROUND_TILESET = initAndGetBackgroundTiledMapTileSet();
        BACKGROUND_LAYER = initAndGetBackgroundLayer();
        BACKGROUND_TILED_MAP = initAndGetBackgroundTiledMap();
        TILED_MAP_RENDERER = initAndGetTiledMapRenderer();
        DEFAULT_SKIN = initAndGetDefaultSkin();
    }

    private Skin initAndGetDefaultSkin() {
        return new Skin(Gdx.files.internal("ui/kenvector_future.json"),
                new TextureAtlas("ui/kenvector_future.atlas"));
    }

    public static AssetUtil getInstance() {
        if (instance == null) {
            instance = new AssetUtil();
        }
        return instance;
    }

    private TiledMapTileLayer initAndGetBackgroundLayer() {
        TiledMapTileLayer layer = new TiledMapTileLayer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                1, 1);
        int id = 0;
        for (int w = 0; w < Gdx.graphics.getWidth() / BACKGROUND_TILE_WIDTH + 1; w++) {
            for (int h = 0; h < Gdx.graphics.getHeight() / BACKGROUND_TILE_HEIGHT + 1; h++, id++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(BACKGROUND_TILESET.getTile(id));
                layer.setCell(BACKGROUND_TILE_WIDTH * w, BACKGROUND_TILE_HEIGHT * h, cell);
            }
        }
        return layer;
    }

    private Texture initAndGetBackgroundTileTexture() {
        return new Texture("tile/darkPurple.png");
    }

    private TextureAtlas getTextureAtlas() {
        return new TextureAtlas("atlas/asteroid-pack.atlas");
    }

    private Array<Sprite> initAndGetSprites() {
        return TEXTURE_ATLAS.createSprites();
    }

    private SpriteBatch initAndGetSpriteBatch() {
        return new SpriteBatch();
    }

    private TiledMapRenderer initAndGetTiledMapRenderer() {
        return new OrthogonalTiledMapRenderer(BACKGROUND_TILED_MAP, SPRITE_BATCH);
    }

    private TiledMap initAndGetBackgroundTiledMap() {
        TiledMap map = new TiledMap();
        map.getLayers().add(BACKGROUND_LAYER);
        map.getTileSets().addTileSet(BACKGROUND_TILESET);
        return map;
    }

    private TiledMapTileSet initAndGetBackgroundTiledMapTileSet() {
        TiledMapTileSet set = new TiledMapTileSet();
        int id = 0;
        for (int w = 0; w < Gdx.graphics.getWidth() / BACKGROUND_TILE_WIDTH + 1; w++) {
            for (int h = 0; h < Gdx.graphics.getHeight() / BACKGROUND_TILE_HEIGHT + 1; h++, id++) {
                set.putTile(id, new StaticTiledMapTile(new TextureRegion(BACKGROUND_TILE_TEXTURE)));
            }
        }
        return set;
    }

    public Array<Sprite> getSprites() {
        return this.SPRITES;
    }

    public SpriteBatch getSpriteBatch() {
        return this.SPRITE_BATCH;
    }

    public TiledMapRenderer getTiledMapRenderer() {
        return this.TILED_MAP_RENDERER;
    }

    public Skin getDefaultSkin() {
        return this.DEFAULT_SKIN;
    }

    /**
     * Method that used to update the whole background tiled map
     */
    public void updateBackgroundTileMap() {
        BACKGROUND_TILESET = initAndGetBackgroundTiledMapTileSet();
        BACKGROUND_LAYER = initAndGetBackgroundLayer();
        BACKGROUND_TILED_MAP = initAndGetBackgroundTiledMap();
        TILED_MAP_RENDERER = initAndGetTiledMapRenderer();
    }

    @Override
    public void dispose() {
        TEXTURE_ATLAS.dispose();
        SPRITE_BATCH.dispose();
        BACKGROUND_TILE_TEXTURE.dispose();
        BACKGROUND_TILED_MAP.dispose();
    }

}
