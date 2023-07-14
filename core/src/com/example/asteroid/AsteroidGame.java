package com.example.asteroid;

import com.badlogic.gdx.Game;
import com.example.asteroid.screen.GameScreen;

public class AsteroidGame extends Game {
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

}
