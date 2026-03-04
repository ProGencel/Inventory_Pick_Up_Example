package com.myname.game.events.itemEvent;

import com.badlogic.gdx.physics.box2d.Body;
import com.myname.game.entities.StaticEntity;
import com.myname.game.screens.gamescreen.inventory.Item;

public class ItemEvent {

    private Item item;
    private StaticEntity staticEntity;

    public ItemEvent(Item item)
    {
        this.item = item;
    }

    public ItemEvent(Item item, StaticEntity staticEntity)
    {
        this.item = item;
        this.staticEntity = staticEntity;
    }

    public Item getItem() {
        return item;
    }

    public StaticEntity getStaticEntity()
    {
        return staticEntity;
    }
}
