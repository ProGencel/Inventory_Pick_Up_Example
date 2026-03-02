package com.myname.game.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.myname.game.entities.HolderStatics;
import com.myname.game.entities.player.Player;
import com.myname.game.events.EventManager;
import com.myname.game.events.playerStatusEvent.PlayerStatusEvent;
import com.myname.game.events.playerStatusEvent.PlayerStatusEventListener;
import com.myname.game.screens.gamescreen.inventory.Inventory;
import com.myname.game.screens.gamescreen.physic.ContactHandler;
import com.myname.game.screens.gamescreen.physic.PhysicWorld;
import com.myname.game.screens.gamescreen.states.InventoryState;
import com.myname.game.screens.gamescreen.tools.MapCamManager;
import com.myname.game.screens.gamescreen.utils.Constants;

public class GameScreen implements Screen, PlayerStatusEventListener {

    private MapCamManager manager;
    private PhysicWorld physicWorld;

    private AssetManager assetManager;

    private SpriteBatch batch;

    private HolderStatics holderStatics;

    private Player player;

    private ContactHandler contactHandler;

    private InputMultiplexer inputMultiplexer;

    private Inventory inventory;

    private Stage stage;

    public GameScreen(AssetManager assetManager)
    {
        this.assetManager = assetManager;
        batch = new SpriteBatch();
        stage = new Stage(new ExtendViewport(Constants.BASIC_SCREEN_WIDTH,Constants.BASIC_SCREEN_HEIGHT));

        manager = new MapCamManager(assetManager,batch);
        physicWorld = new PhysicWorld(manager);
        contactHandler = new ContactHandler(physicWorld.getWorld());

        holderStatics = new HolderStatics(manager.getTiledMap(),physicWorld.getWorld());
        player = new Player(assetManager,manager.getTiledMap(),physicWorld.getWorld());

        inventory = new Inventory(assetManager.get("Sprites/Char.atlas"),stage);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inventory.getStage());
        inputMultiplexer.addProcessor(player.getPlayerController());

        Gdx.input.setInputProcessor(inputMultiplexer);
        stage.setDebugAll(true);

        EventManager.subscribe(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        physicWorld.updatePhysic(delta);

        ScreenUtils.clear(Color.GRAY);

        manager.cameraUpdate(delta,player);
        manager.mapRender(delta);

        batch.setProjectionMatrix(manager.getCamera().combined);
        batch.begin();

        holderStatics.draw(batch);
        player.render(delta,batch);

        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.E))
        {
            EventManager.newPlayerStatusEvent(new PlayerStatusEvent(new InventoryState()));
            inventory.onVisible();
        }

        stage.act();
        stage.draw();

        physicWorld.render();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        manager.dispose();
        batch.dispose();
        physicWorld.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        manager.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void responsePlayerStatusEvent(PlayerStatusEvent event) {
        player.getPlayerController().setPlayerState(player.getIdleState());
        inputMultiplexer.removeProcessor(player.getPlayerController());
    }
}
