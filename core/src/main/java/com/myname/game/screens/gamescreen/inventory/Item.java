package com.myname.game.screens.gamescreen.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

    private int ID;
    private String name;
    private int amount;
    private TextureRegion icon;

    public Item(int ID, String name, int amount, TextureRegion icon) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
