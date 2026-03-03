package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.myname.game.entities.player.Player;
import com.myname.game.screens.gamescreen.utils.Constants;

public class WalkingState implements State{

    private Player player;
    private Vector2 currentSpeed;

    public WalkingState(Player player)
    {
        this.player = player;
        currentSpeed = new Vector2(0,0);
    }

    @Override
    public void input(int keycode) {
    }

    @Override
    public void update(float dt) {

        currentSpeed.x = 0;
        currentSpeed.y = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            currentSpeed.y = 1;
            player.setDirection(Player.Direction.UP);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            currentSpeed.y = -1;
            player.setDirection(Player.Direction.DOWN);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            currentSpeed.x = -1;
            player.setDirection(Player.Direction.LEFT);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            currentSpeed.x = 1;
            player.setDirection(Player.Direction.RIGHT);
        }

        if(currentSpeed.isZero())
        {
            player.getPlayerController().setPlayerState(player.getIdleState());
        }

        if(currentSpeed.x != 0 || currentSpeed.y != 0)
        {
            currentSpeed.nor().scl(Constants.PLAYER_SPEED);
        }

        player.getBody().setLinearVelocity(currentSpeed);

    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {
    }

    @Override
    public EnumState getEnumState() {
        return EnumState.WALK;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        if(player.getDirection().equals(Player.Direction.RIGHT))
        {
            return player.walkRightAnimation;
        }
        if(player.getDirection().equals(Player.Direction.LEFT))
        {
            return player.walkLeftAnimation;
        }
        if(player.getDirection().equals(Player.Direction.UP))
        {
            return player.walkUpAnimation;
        }
        if(player.getDirection().equals(Player.Direction.DOWN))
        {
            return player.walkDownAnimation;
        }
        return null;
    }

}
