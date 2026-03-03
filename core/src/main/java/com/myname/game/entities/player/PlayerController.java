package com.myname.game.entities.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.myname.game.events.EventManager;
import com.myname.game.events.playerStatusEvent.PlayerStatusEvent;
import com.myname.game.screens.gamescreen.states.IdleState;
import com.myname.game.screens.gamescreen.states.InventoryState;
import com.myname.game.screens.gamescreen.states.State;
import com.myname.game.screens.gamescreen.states.WalkingState;
import com.myname.game.screens.gamescreen.utils.ExceptionSafety;

public class PlayerController implements InputProcessor {

    private State playerState;

    public PlayerController(Player player)
    {
        setPlayerState(player.getIdleState());
    }

    public void setPlayerState(State state)
    {
        ExceptionSafety.safelyExitState(playerState);
        playerState = state;
        state.enter();
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.E)
        {
            EventManager.newPlayerStatusEvent(new PlayerStatusEvent(State.EnumState.INVENTORY));
        }
        else
        {
            playerState.input(keycode);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public State getPlayerState()
    {
        return playerState;
    }
}
