package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.Input;
import com.myname.game.entities.player.Player;
import com.myname.game.screens.gamescreen.utils.ExceptionSafety;

public class IdleState implements State{

    private Player player;

    public IdleState(Player player)
    {
        this.player = player;
    }

    @Override
    public void input(int keycode) {

        boolean isWalking = (Input.Keys.A == keycode || Input.Keys.D  == keycode ||
            Input.Keys.W  == keycode || Input.Keys.S == keycode );

        if(isWalking)
        {
            player.getPlayerController().setPlayerState(new WalkingState(player));
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void enter() {
        System.out.println("IdleState entered");
        ExceptionSafety.setPlayerVelocityToZeroSafely(player);
    }

    @Override
    public void exit() {
        System.out.println("IdleState exited");
    }
}
