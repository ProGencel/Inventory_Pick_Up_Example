package com.myname.game.events.itemEvent;

import com.myname.game.screens.gamescreen.inventory.Item;

public class ItemEvent {

    private Item item;

    public ItemEvent(Item item)
    {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
