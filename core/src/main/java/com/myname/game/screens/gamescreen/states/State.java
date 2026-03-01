package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface State {

    Animation<TextureRegion> getAnimation();

    void input(int keycode);

    void update(float dt);

    void enter();
    void exit();

}
