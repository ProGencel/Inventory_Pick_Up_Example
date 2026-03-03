package com.myname.game.screens.gamescreen.inventory;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.myname.game.events.EventManager;
import com.myname.game.events.playerStatusEvent.PlayerStatusEvent;
import com.myname.game.events.playerStatusEvent.PlayerStatusEventListener;
import com.myname.game.screens.gamescreen.states.InventoryState;
import com.myname.game.screens.gamescreen.states.State;

public class Inventory implements PlayerStatusEventListener {

    private Stage stage;
    private Table mainTable;
    private Table slotTable;

    private Array<Slot> slotArray;

    public Inventory(TextureAtlas atlas,Stage stage)
    {
        this.stage = stage;

        slotArray = new Array<>();
        slotTable = new Table();
        setSlots(new TextureRegionDrawable(atlas.findRegion("slot")));

        mainTable = new Table();
        mainTable.setFillParent(true);
        offVisible();

        mainTable.add(slotTable);
        setMainTableBackground();

        stage.addActor(mainTable);

        EventManager.subscribe(this);
    }

    public void onVisible()
    {
        onEverything();
    }

    public void offVisible()
    {
        offEverything();
    }

    public Stage getStage()
    {
        return stage;
    }

    private void offEverything()
    {
        mainTable.setVisible(false);
        mainTable.setTouchable(Touchable.disabled);
    }

    private void onEverything()
    {
        mainTable.setVisible(true);
        mainTable.setTouchable(Touchable.enabled);
    }

    private void setSlots(TextureRegionDrawable texture)
    {
        for(int i = 0; i<5;i++)
        {
            Slot slot = new Slot(texture);
            slotArray.add(slot);
            slotTable.add(slot);
        }
    }

    private void setMainTableBackground()
    {
        Pixmap pixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0,0,0,0.7f);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);

        mainTable.setBackground(drawable);

        pixmap.dispose();
    }

    @Override
    public void responsePlayerStatusEvent(PlayerStatusEvent event) {

        if(event.getEnumState().equals(State.EnumState.INVENTORY))
        {
            if(!mainTable.isVisible())
            {
                onVisible();
            }
            else
            {
                offVisible();
            }
        }
    }
}
