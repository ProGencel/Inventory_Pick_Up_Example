package com.myname.game.screens.gamescreen.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.myname.game.screens.gamescreen.tools.MapCamManager;

public class PhysicWorld {

    private World world;
    private float accumulator = 0;

    private Box2DDebugRenderer debugRenderer;

    private MapCamManager manager;

    public PhysicWorld(MapCamManager manager)
    {
        this.manager = manager;

        world = new World(new Vector2(0,0),true);
        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(float dt)
    {
        doPhysicsStep(dt);
        debugRenderer.render(world,manager.getCamera().combined);
    }



    private void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f,6,2);
            accumulator -= 1/60f;
        }
    }

    public World getWorld()
    {
        return world;
    }

    public void dispose()
    {
        world.dispose();
    }

}
