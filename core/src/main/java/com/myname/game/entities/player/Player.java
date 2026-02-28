package com.myname.game.entities.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.myname.game.entities.GameEntity;
import com.myname.game.screens.gamescreen.utils.StaticMethods;

public class Player extends GameEntity {

    private Texture texture;
    private TextureRegion textureRegion;

    private Ellipse ellipse;

    private Body body;

    private EllipseMapObject playerCircObj;

    private Array<MapObject> mapObjectArray;

    private PlayerRenderer playerRenderer;

    public Player(AssetManager manager, TiledMap map, World world)
    {
        playerCircObj = (EllipseMapObject) StaticMethods.findWantedMapObject(map,"Objects","Player", EllipseMapObject.class);

        ellipse = playerCircObj.getEllipse();

        StaticMethods.ppmShape(ellipse);

        playerRenderer = new PlayerRenderer(manager,this);

        body = StaticMethods.createBody(BodyDef.BodyType.DynamicBody,world,new Vector2(ellipse.x,ellipse.y),
            new Vector2(ellipse.width,ellipse.height), StaticMethods.ShapeType.Ellipse);
    }

    public void render(float dt, SpriteBatch batch)
    {
        playerRenderer.render(dt,batch);
    }

    public Ellipse getEllipse()
    {
        return ellipse;
    }

}
