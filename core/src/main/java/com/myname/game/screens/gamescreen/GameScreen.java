package com.myname.game.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.myname.game.entities.HolderStatics;
import com.myname.game.entities.player.Player;
import com.myname.game.screens.gamescreen.physic.PhysicWorld;
import com.myname.game.screens.gamescreen.tools.MapCamManager;

public class GameScreen implements Screen {

    private MapCamManager manager;
    private PhysicWorld physicWorld;

    private AssetManager assetManager;

    private SpriteBatch batch;

    private HolderStatics holderStatics;

    private Player player;

    public GameScreen(AssetManager assetManager)
    {
        this.assetManager = assetManager;
        batch = new SpriteBatch();

        manager = new MapCamManager(assetManager,batch);
        physicWorld = new PhysicWorld(manager);

        holderStatics = new HolderStatics(manager.getTiledMap(),physicWorld.getWorld());
        player = new Player(assetManager,manager.getTiledMap(),physicWorld.getWorld());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.GRAY);

        manager.render(delta);

        batch.setProjectionMatrix(manager.getCamera().combined);
        batch.begin();

        holderStatics.draw(batch);
        player.render(delta,batch);

        batch.end();

        physicWorld.render(delta);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        manager.dispose();
        batch.dispose();
        physicWorld.dispose();
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
}
