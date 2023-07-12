package com.example.asteroid.actor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.IntSet;
import com.example.asteroid.stage.GameStage;

public class StarShip extends Group {

    @Override
    public void act(float delta) {
        super.act(delta);
        IntSet keys = ((GameStage) getStage()).getKeys();
        MovableMaterial starShipPhysics = (MovableMaterial) getChildren().get(0);
        if (keys.contains(Input.Keys.A)) {

        }
        if (keys.contains(Input.Keys.W)) {

        }
        if (keys.contains(Input.Keys.D)) {

        }

    }

}
