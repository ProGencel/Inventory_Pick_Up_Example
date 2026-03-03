package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myname.game.entities.player.Player;

public class InventoryState implements State{

    @Override
    public EnumState getEnumState() {
        return EnumState.INVENTORY;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        return null;
    }

    @Override
    public void input(int keycode) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {

    }
}
