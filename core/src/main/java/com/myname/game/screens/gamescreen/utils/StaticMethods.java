package com.myname.game.screens.gamescreen.utils;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class StaticMethods {

    public enum ShapeType
    {
        Rectangle,
        Ellipse,
        Polygon
    }

    public static <T extends MapObject> Array<MapObject> findWantedMapbjects(TiledMap map, String mapLayer, String wantedClass, Class<T> clazz)
    {
        Array<MapObject> array = new Array<>();

        MapLayer layer = ExceptionSafety.safeLayer(map,mapLayer);

        for(MapObject mapObject : layer.getObjects().getByType(clazz))
        {
            String wantedType = mapObject.getProperties().get("type", String.class);

            if(wantedType.equals(wantedClass))
            {
               array.add(mapObject);
            }
        }

        return array;
    }

    public static <T extends MapObject> MapObject findWantedMapbject(TiledMap map, String mapLayer, String wantedClass, Class<T> clazz)
    {
        MapLayer layer = ExceptionSafety.safeLayer(map,mapLayer);

        for(MapObject mapObject : layer.getObjects().getByType(clazz))
        {
            String wantedType = mapObject.getProperties().get("type", String.class);

            if(wantedType.equals(wantedClass))
            {
                return mapObject;
            }
        }

        throw new IllegalStateException("Object cannot find");
    }

    public static Body createBody(BodyDef.BodyType bodyType, World world, Vector2 pos,Vector2 mes, ShapeType shapeType)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(pos.x + mes.x/2,pos.y + mes.y/2);

        Body body = world.createBody(bodyDef);

        Shape shape = null;
        FixtureDef fdef = new FixtureDef();

        switch (shapeType)
        {
            case Ellipse -> {
                shape = new CircleShape();
                shape.setRadius(mes.x/2);

                fdef.shape = shape;
            }
            case Rectangle -> {
                shape = new PolygonShape();
                PolygonShape polygonShape = (PolygonShape) shape;
                polygonShape.setAsBox(mes.x/2,mes.y/2);

                fdef.shape = polygonShape;
            }
        }

        Fixture fixture = body.createFixture(fdef);

        assert shape != null : "Shape error !";
        shape.dispose();

        return body;
    }

    public static void ppmShape(Rectangle rectangle)
    {
        rectangle.x *= PPM;
        rectangle.y *= PPM;
        rectangle.height *= PPM;
        rectangle.width *= PPM;
    }

    public static void ppmShape(Ellipse ellipse)
    {
        ellipse.x *= PPM;
        ellipse.y *= PPM;
        ellipse.height *= PPM;
        ellipse.width *= PPM;
    }

}
