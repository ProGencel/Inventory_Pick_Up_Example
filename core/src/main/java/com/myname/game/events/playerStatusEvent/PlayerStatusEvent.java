package com.myname.game.events.playerStatusEvent;

import com.myname.game.screens.gamescreen.states.State;

public class PlayerStatusEvent {

    public PlayerStatusEvent(State state)
    {
        playerState = state;
    }

    public State playerState;

}
