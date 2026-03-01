package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myname.game.entities.player.Player;
import com.myname.game.screens.gamescreen.utils.ExceptionSafety;

public class IdleState implements State{

    private Player player;

    public IdleState(Player player)
    {
        this.player = player;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        if(player.getDirection().equals(Player.Direction.RIGHT))
        {
            return player.idleRightAnimation;
        }
        if(player.getDirection().equals(Player.Direction.LEFT))
        {
            return player.idleLeftAnimation;
        }
        if(player.getDirection().equals(Player.Direction.UP))
        {
            return player.idleUpAnimation;
        }
        if(player.getDirection().equals(Player.Direction.DOWN))
        {
            return player.idleDownAnimation;
        }
        return null;
    }

    @Override
    public void input(int keycode) {

        boolean isWalking = (Input.Keys.A == keycode || Input.Keys.D  == keycode ||
            Input.Keys.W  == keycode || Input.Keys.S == keycode );

        if(isWalking)
        {
            player.getPlayerController().setPlayerState(player.getWalkingState());
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
