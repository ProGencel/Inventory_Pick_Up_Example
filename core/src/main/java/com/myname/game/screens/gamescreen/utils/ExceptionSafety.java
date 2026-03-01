package com.myname.game.screens.gamescreen.utils;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.myname.game.entities.player.Player;
import com.myname.game.screens.gamescreen.states.State;

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

    public static String safeTiledClass(MapObject mapObject, String wantedClass)
    {
        if(mapObject.getProperties().get("type", String.class) == null)
        {
            throw new IllegalStateException(wantedClass+" cannot find");
        }
        else
        {
            return mapObject.getProperties().get("type", String.class);
        }
    }

    public static void safelyExitState(State state)
    {
        try{
            state.exit();
        } catch (Exception e)
        {

        }
    }

    public static void setPlayerVelocityToZeroSafely(Player player)
    {
        try{
            player.getBody().setLinearVelocity(0,0);
        }catch (Exception e)
        {

        }
    }

}
