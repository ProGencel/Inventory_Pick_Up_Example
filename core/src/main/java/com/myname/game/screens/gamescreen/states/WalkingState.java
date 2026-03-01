package com.myname.game.screens.gamescreen.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    public enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public Direction direction = Direction.RIGHT;

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
            direction = Direction.UP;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            currentSpeed.y = -1;
            direction = Direction.DOWN;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            currentSpeed.x = -1;
            direction = Direction.LEFT;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            currentSpeed.x = 1;
            direction = Direction.RIGHT;
        }

        if(currentSpeed.isZero())
        {
            player.getPlayerController().setPlayerState(new IdleState(player));
        }

        if(currentSpeed.x != 0 || currentSpeed.y != 0)
        {
            currentSpeed.nor().scl(Constants.PLAYER_SPEED);
        }

        player.getBody().setLinearVelocity(currentSpeed);

    }

    @Override
    public void enter() {
        System.out.println("WalkingState entered");
    }

    @Override
    public void exit() {
        System.out.println("WalkingState exited");
    }
}
