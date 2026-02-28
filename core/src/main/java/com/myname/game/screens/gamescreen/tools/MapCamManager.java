package com.myname.game.screens.gamescreen.tools;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myname.game.entities.player.Player;

public class MapCamManager {

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public MapCamManager(AssetManager manager, SpriteBatch batch)
    {
        tiledMap = manager.get("World/map.tmx");
        camera = new OrthographicCamera();
        viewport = new FitViewport(BASIC_SCREEN_WIDTH * PPM,BASIC_SCREEN_HEIGHT * PPM,camera);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, PPM,batch);

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    public void update(float dt, Player player)
    {
        camera.position.x = player.getEllipse().x;
        camera.position.y = player.getEllipse().y;
        camera.update();
    }

    public void render(float dt, Player player)
    {
        update(dt, player);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void dispose()
    {
        tiledMapRenderer.dispose();
    }

    public TiledMap getTiledMap()
    {
        return tiledMap;
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
