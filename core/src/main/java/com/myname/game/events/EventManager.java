package com.myname.game.events;

import com.badlogic.gdx.utils.Array;
import com.myname.game.events.playerStatusEvent.PlayerStatusEvent;
import com.myname.game.events.playerStatusEvent.PlayerStatusEventListener;

public class EventManager {

    public static Array<PlayerStatusEventListener> playerStatusEventListeners = new Array<>();

    public static void newPlayerStatusEvent(PlayerStatusEvent event)
    {
        for(PlayerStatusEventListener listener : playerStatusEventListeners)
        {
            listener.responsePlayerStatusEvent(event);
        }
    }

    public static void subscribe(PlayerStatusEventListener listener)
    {
        playerStatusEventListeners.add(listener);
    }

}
