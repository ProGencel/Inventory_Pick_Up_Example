package com.myname.game.screens.gamescreen.states;

public interface State {

    public void input(int keycode);

    public void update(float dt);

    public void enter();
    public void exit();

}
