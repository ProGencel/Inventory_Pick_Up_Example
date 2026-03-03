package com.myname.game.events.playerStatusEvent;

import com.myname.game.screens.gamescreen.states.State;

public class PlayerStatusEvent {

    private State.EnumState enumState;

    public PlayerStatusEvent(State.EnumState enumState)
    {
        this.enumState = enumState;
    }

    public State.EnumState getEnumState() {
        return enumState;
    }
}
