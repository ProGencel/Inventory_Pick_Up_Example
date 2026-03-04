package com.myname.game.screens.gamescreen.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

    private int ID;
    private String name;
    private TextureRegion icon;

    public Item(int ID, String name, TextureRegion icon) {
        this.ID = ID;
        this.name = name;
        this.icon = icon;
    }

    public TextureRegion getIcon() {
        return icon;
    }

    public void setIcon(TextureRegion icon) {
        this.icon = icon;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
