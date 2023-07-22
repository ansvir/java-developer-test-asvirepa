package com.example.asteroid;

import com.badlogic.gdx.Game;
import com.example.asteroid.screen.GameScreen;
import com.example.asteroid.screen.MenuScreen;
import com.example.asteroid.util.ActorUtil;
import com.example.asteroid.util.AssetUtil;

/**
 * Game entrance point class
 */
public class AsteroidGame extends Game {
	
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		AssetUtil.getInstance().dispose();
	}

}
