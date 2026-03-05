package com.myname.game.entities;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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
    private Fixture fixture;

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
        body = StaticMethods.createBody(BodyDef.BodyType.StaticBody,world,
            new Vector2(mapObject.getX()*PPM,mapObject.getY()*PPM),
            new Vector2(mapObject.getTextureRegion().getRegionWidth()*PPM,mapObject.getTextureRegion().getRegionHeight()*PPM));

        body.setUserData(this);

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
            FixtureDef fdef = new FixtureDef();
            fixture = StaticMethods.createFixture(body,fdef, StaticMethods.ShapeType.Rectangle,
                new Vector2(rec.width,rec.height));
            fixture.setUserData(STATIC_DATA);
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
