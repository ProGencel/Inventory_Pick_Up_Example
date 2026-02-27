package com.myname.game.screens.gamescreen.utils;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class ExceptionSafety {

    public static MapLayer safeLayer(TiledMap map, String layerName)
    {
        MapLayer layer = map.getLayers().get(layerName);
        if(layer == null)
        {
            throw new IllegalStateException(layerName+" cannot find");
        }
        else
        {
            return layer;
        }
    }

}
