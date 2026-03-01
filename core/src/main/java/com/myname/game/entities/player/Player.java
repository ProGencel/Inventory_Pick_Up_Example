package com.myname.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.myname.game.screens.gamescreen.states.IdleState;
import com.myname.game.screens.gamescreen.states.WalkingState;
import com.myname.game.screens.gamescreen.utils.StaticMethods;

public class Player extends GameEntity {

    public enum Direction
    {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    private IdleState idleState;
    private WalkingState walkingState;

    private Direction direction;

    private Texture texture;
    private TextureRegion textureRegion;

    private Ellipse ellipse;

    private Body body;

    private EllipseMapObject playerCircObj;

    private TextureAtlas atlas;

    public Animation<TextureRegion> idleLeftAnimation;
    public Animation<TextureRegion> idleRightAnimation;
    public Animation<TextureRegion> idleUpAnimation;
    public Animation<TextureRegion> idleDownAnimation;

    public Animation<TextureRegion> walkLeftAnimation;
    public Animation<TextureRegion> walkRightAnimation;
    public Animation<TextureRegion> walkUpAnimation;
    public Animation<TextureRegion> walkDownAnimation;

    private PlayerRenderer playerRenderer;

    public IdleState getIdleState() {
        return idleState;
    }

    public WalkingState getWalkingState() {
        return walkingState;
    }

    private PlayerController playerController;

    public Player(AssetManager manager, TiledMap map, World world)
    {
        atlas = manager.get("Sprites/Char.atlas", TextureAtlas.class);
        setTextures();

        idleState = new IdleState(this);
        walkingState = new WalkingState(this);

        direction = Direction.RIGHT;

        playerCircObj = (EllipseMapObject) StaticMethods.findWantedMapObject(map,"Objects","Player", EllipseMapObject.class);
        ellipse = playerCircObj.getEllipse();

        StaticMethods.ppmShape(ellipse);

        playerRenderer = new PlayerRenderer(this);
        playerController = new PlayerController(this);
        Gdx.input.setInputProcessor(playerController);

        body = StaticMethods.createBody(BodyDef.BodyType.DynamicBody,world,new Vector2(ellipse.x,ellipse.y),
            new Vector2(ellipse.width,ellipse.height), StaticMethods.ShapeType.Ellipse);
    }

    public void render(float dt, SpriteBatch batch)
    {
        playerRenderer.render(dt,batch);
        playerController.getPlayerState().update(dt);
    }

    public PlayerController getPlayerController()
    {
        return playerController;
    }

    public Body getBody() {
        return body;
    }

    private void setTextures()
    {
        Array<TextureAtlas.AtlasRegion> frames = atlas.findRegions("idle_right");
        idleRightAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);

        frames = atlas.findRegions("idle_left");
        idleLeftAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);

        frames = atlas.findRegions("idle_up");
        idleUpAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);

        frames = atlas.findRegions("idle_down");
        idleDownAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);


        frames = atlas.findRegions("walk_right");
        walkRightAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);

        frames = atlas.findRegions("walk_left");
        walkLeftAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);

        frames = atlas.findRegions("walk_up");
        walkUpAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);

        frames = atlas.findRegions("walk_down");
        walkDownAnimation = new Animation<>(0.4f,frames, Animation.PlayMode.LOOP);
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public Direction getDirection()
    {
        return direction;
    }
}
