package com.myname.game.screens.gamescreen.inventory;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Inventory {

    private Stage stage;
    private Table mainTable;

    public Inventory(AssetManager assetManager,Stage stage)
    {
        this.stage = stage;

        mainTable = new Table();
        mainTable.setFillParent(true);
        offVisible();

        stage.addActor(mainTable);
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

}
