package com.myname.game.events;

import com.badlogic.gdx.utils.Array;
import com.myname.game.events.itemEvent.ItemEvent;
import com.myname.game.events.itemEvent.ItemEventListener;
import com.myname.game.events.playerStatusEvent.PlayerStatusEvent;
import com.myname.game.events.playerStatusEvent.PlayerStatusEventListener;

public class EventManager {

    public static Array<PlayerStatusEventListener> playerStatusEventListeners = new Array<>();
    public static Array<ItemEventListener> itemEventListeners = new Array<>();

    public static void newPlayerStatusEvent(PlayerStatusEvent event)
    {
        for(PlayerStatusEventListener listener : playerStatusEventListeners)
        {
            listener.responsePlayerStatusEvent(event);
        }
    }

    public static void newItemEvent(ItemEvent event)
    {
        for(ItemEventListener listener : itemEventListeners)
        {
            listener.responseItemEvent(event);
        }
    }

    public static void subscribe(PlayerStatusEventListener listener)
    {
        playerStatusEventListeners.add(listener);
    }

    public static void subscribe(ItemEventListener listener)
    {
        itemEventListeners.add(listener);
    }

}
