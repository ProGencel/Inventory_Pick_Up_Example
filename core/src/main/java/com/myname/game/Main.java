package com.myname.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.myname.game.screens.gamescreen.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private AssetManager manager;
    private TmxMapLoader mapLoader;

    @Override
    public void create() {
        manager = new AssetManager();
        mapLoader = new TmxMapLoader();

        loadAssets();

        setScreen(new GameScreen(manager));
    }

    private void loadAssets()
    {
        manager.setLoader(TiledMap.class,mapLoader);
        manager.load("World/map.tmx", TiledMap.class);
        manager.load("Sprites/Char.atlas", TextureAtlas.class);
        manager.finishLoading();
    }
}
