package com.myname.game.entities.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.myname.game.entities.GameEntity;
import com.myname.game.screens.gamescreen.physic.StaticMethods;

public class Player extends GameEntity {

    private Texture texture;

    private Rectangle rectangle;

    private Body body;

    private RectangleMapObject playerRecObj;

    private Array<MapObject> mapObjectArray;

    public Player(AssetManager manager, TiledMap map, World world)
    {
        texture = manager.get("bunnyWalk.png");
        playerRecObj = (RectangleMapObject) StaticMethods.findWantedMapbject(map,"Objects","PlayerRec", RectangleMapObject.class);
        rectangle = playerRecObj.getRectangle();
        StaticMethods.ppmRectangle(rectangle);

        body = StaticMethods.createBody(BodyDef.BodyType.DynamicBody,world,new Vector2(rectangle.x,rectangle.y),
            new Vector2(rectangle.width,rectangle.height), StaticMethods.ShapeType.Rectangle);
    }

}
