package com.example.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.asteroid.screen.GameScreen;
import com.example.asteroid.util.AssetUtil;

public class AsteroidGame extends Game {
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

}
