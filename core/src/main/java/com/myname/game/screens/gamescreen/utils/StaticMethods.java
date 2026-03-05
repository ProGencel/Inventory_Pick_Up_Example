package com.myname.game.screens.gamescreen.utils;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
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

    public static <T extends MapObject> Array<MapObject> findWantedMapObjects(TiledMap map, String mapLayer, String wantedClass, Class<T> clazz)
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

    public static <T extends MapObject> MapObject findWantedMapObject(TiledMap map, String mapLayer, String wantedClass, Class<T> clazz)
    {
        MapLayer layer = ExceptionSafety.safeLayer(map,mapLayer);

        for(MapObject mapObject : layer.getObjects().getByType(clazz))
        {
            String wantedType = ExceptionSafety.safeTiledClass(mapObject,wantedClass);

            if(wantedType.equals(wantedClass))
            {
                return mapObject;
            }
        }

        throw new IllegalStateException("Object cannot find");
    }

    public static Body createBody(BodyDef.BodyType bodyType, World world, Vector2 pos,Vector2 bodyMeasure)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(pos.x + bodyMeasure.x/2,pos.y + bodyMeasure.y/2);

        Body body = world.createBody(bodyDef);

        return body;
    }

    public static Fixture createFixture(Body body,FixtureDef fdef, ShapeType shapeType, Vector2 fixtureMeasure)
    {

        Shape shape = null;

        switch (shapeType)
        {
            case Ellipse -> {
                shape = new CircleShape();
                shape.setRadius(fixtureMeasure.x/2);

                fdef.shape = shape;
            }
            case Rectangle -> {
                shape = new PolygonShape();
                PolygonShape polygonShape = (PolygonShape) shape;
                polygonShape.setAsBox(fixtureMeasure.x/2,fixtureMeasure.y/2);

                fdef.shape = polygonShape;
            }
        }

        assert shape != null : "The shape is null";
        Fixture fixture = body.createFixture(fdef);
        return fixture;

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

    public static boolean isTheyTheLookingFixtures(int dataA, int dataB,Object dataAA, Object dataBB)
    {
        if(dataAA == null || dataBB == null)
        {
            throw new IllegalStateException("Object data cannot find on contact");
        }

        if(dataA == (int)dataAA && dataB == (int)dataBB)
        {
            return true;
        }
        else if(dataB == (int)dataAA && dataA == (int)dataBB)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
