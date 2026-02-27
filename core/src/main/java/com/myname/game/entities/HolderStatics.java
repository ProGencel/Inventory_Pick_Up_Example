package com.myname.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.PointMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.myname.game.screens.gamescreen.physic.StaticMethods;

public class HolderStatics {

    private Array<StaticEntity> statics;

    public HolderStatics(TiledMap map, World world)
    {
        statics = new Array<>();
        setStatics(map,"Objects", world);
    }

    private void setStatics(TiledMap map, String mapLayer, World world)
    {
        MapLayer layer = map.getLayers().get(mapLayer);

        for(TiledMapTileMapObject mapObject : layer.getObjects().getByType(TiledMapTileMapObject.class))
        {
            StaticEntity staticEntity = new StaticEntity(mapObject,world,map);
            statics.add(staticEntity);
        }
    }

    public void draw(SpriteBatch batch)
    {
        for(StaticEntity entity : statics)
        {
            entity.draw(batch);
        }
    }

}
