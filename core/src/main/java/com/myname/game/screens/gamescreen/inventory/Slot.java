package com.myname.game.screens.gamescreen.inventory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Slot extends Button {

    private Item item = null;
    private Stack stack;
    private Image itemImage;

    public Slot(TextureRegionDrawable texture)
    {
        super(texture);
        stack = new Stack();
        itemImage = new Image();
        stack.add(itemImage);

        this.add(stack).fill().expand();
    }

    public void setItem(Item item)
    {
        this.item = item;
        if(item == null)
        {
            itemImage.setDrawable(null);
        }
        else
        {
            itemImage.setDrawable(new TextureRegionDrawable(item.getIcon()));
        }
    }

}
