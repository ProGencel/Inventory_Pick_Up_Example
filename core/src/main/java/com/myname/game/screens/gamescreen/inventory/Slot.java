package com.myname.game.screens.gamescreen.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.myname.game.screens.gamescreen.inventory.Inventory.box;

public class Slot extends Button {

    private Item item = null;
    private Stack stack;
    private Image itemImage;
    private int amount = 0;

    private Table labelTable;
    private Label label;

    public Slot(TextureRegionDrawable texture)
    {
        super(texture);
        stack = new Stack();
        itemImage = new Image();
        stack.add(itemImage);

        labelTable = new Table();
        setLabel();
        labelTable.bottom().right();
        labelTable.add(label).padRight(4).padBottom(2);

        stack.add(labelTable);

        this.add(stack).fill().expand();
    }

    public void setItem(Item eventItem)
    {
        if(eventItem == null)
        {
            itemImage.setDrawable(null);
        }
        else
        {
            if(item == null)
            {
                item = eventItem;
                itemImage.setDrawable(new TextureRegionDrawable(eventItem.getIcon()));
                amount++;
            }
            else
            {
                amount++;
            }
            label.setText(amount);
        }
    }

    public Item getItem()
    {
        return item;
    }

    public void setLabel()
    {
        BitmapFont bitmapFont = new BitmapFont();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font.getData().setScale(0.5f);

        label = new Label(amount == 0 ? " " : String.valueOf(amount),labelStyle);
    }

}
