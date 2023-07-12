package com.example.asteroid.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class AssetUtil implements Disposable {

    private static AssetUtil instance;
    private final Array<TextureAtlas.AtlasSprite> SPRITES;
    private final TextureAtlas TEXTURE_ATLAS;
    private final SpriteBatch SPRITE_BATCH;

    private AssetUtil() {
        TEXTURE_ATLAS = getTextureAtlas();
        SPRITES = initAndGetSprites();
        SPRITE_BATCH = initAndGetSpriteBatch();
    }
    public static AssetUtil getInstance() {
        if (instance == null) {
            instance = new AssetUtil();
        }
        return instance;
    }

    private TextureAtlas getTextureAtlas() {
        return new TextureAtlas("atlas/asteroid-pack.atlas");
    }

    private Array<TextureAtlas.AtlasSprite> initAndGetSprites() {
        Array<TextureAtlas.AtlasRegion> regions = TEXTURE_ATLAS.getRegions();
        Array<TextureAtlas.AtlasSprite> result = new Array<>();
        regions.forEach(r -> result.add(new TextureAtlas.AtlasSprite(r)));
        return result;
    }

    private SpriteBatch initAndGetSpriteBatch() {
        return new SpriteBatch();
    }

    public Array<TextureAtlas.AtlasSprite> getSprites() {
        return this.SPRITES;
    }

    public SpriteBatch getSpriteBatch() {
        return this.SPRITE_BATCH;
    }

    @Override
    public void dispose() {
        TEXTURE_ATLAS.dispose();
        SPRITE_BATCH.dispose();
    }

}
