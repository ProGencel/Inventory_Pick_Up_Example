package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface State {

    enum EnumState
    {
        WALK,
        IDLE,
    }

    EnumState getEnumState();

    Animation<TextureRegion> getAnimation();

    void input(int keycode);

    void update(float dt);

    void enter();
    void exit();

}
