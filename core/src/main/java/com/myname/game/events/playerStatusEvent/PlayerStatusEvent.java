package com.myname.game.events.playerStatusEvent;

import com.myname.game.screens.gamescreen.states.StateGui;

public class PlayerStatusEvent {

    private StateGui stateGui;

    public PlayerStatusEvent(StateGui stateGui)
    {
        this.stateGui = stateGui;
    }

    public StateGui getStateGui() {
        return stateGui;
    }
}
