package com.myname.game.entities;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.myname.game.events.EventManager;
import com.myname.game.events.itemEvent.ItemEvent;
import com.myname.game.screens.gamescreen.interfaces.Interactable;
import com.myname.game.screens.gamescreen.inventory.Inventory;
import com.myname.game.screens.gamescreen.utils.StaticMethods;

public class StaticEntity extends GameEntity implements Interactable {

    private TextureRegion texture;
    private Rectangle rectangle;

    private Body body;

    private boolean isAvailable = true;

    private Array<Rectangle> hitboxRecs;

    public StaticEntity(TiledMapTileMapObject mapObject, World world)
    {
        rectangle = new Rectangle();

        rectangle.x = mapObject.getX() * PPM;
        rectangle.y = mapObject.getY() * PPM;
        rectangle.width = mapObject.getTextureRegion().getRegionWidth() * PPM;
        rectangle.height = mapObject.getTextureRegion().getRegionHeight() * PPM;

        texture = mapObject.getTextureRegion();


        hitboxRecs = new Array<>();
        setHitboxRecs(mapObject,world);
        body.setUserData(this);
    }

    private void setHitboxRecs(TiledMapTileMapObject mapObject, World world)
    {
        for(RectangleMapObject rectangleMapObject : mapObject.getTile().getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = new Rectangle(rectangleMapObject.getRectangle());
            rec.x *= PPM;
            rec.x += rectangle.x;

            rec.y *= PPM;
            rec.y += rectangle.y;

            rec.height *= PPM;
            rec.width *= PPM;

            hitboxRecs.add(rec);
        }

        for(Rectangle rec : hitboxRecs)
        {
            body = StaticMethods.createBody(BodyDef.BodyType.StaticBody,world,new Vector2(rec.x,rec.y),
                new Vector2(rec.width,rec.height), StaticMethods.ShapeType.Rectangle);
            Fixture firstFixture = body.getFixtureList().get(0);
            firstFixture.setUserData(STATIC_DATA);

        }
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }

    @Override
    public void interact() {
        EventManager.newItemEvent(new ItemEvent(Inventory.box,this));
    }

    public void destroyBody(World world)
    {
        world.destroyBody(body);
        isAvailable = false;
    }

    public boolean getIsAvailable()
    {
        return  isAvailable;
    }
}
