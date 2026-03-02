package com.myname.game.screens.gamescreen.physic;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.physics.box2d.*;

public class ContactHandler implements ContactListener{

    public ContactHandler(World world)
    {
        world.setContactListener(this);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object dataA = fixtureA.getUserData();
        Object dataB = fixtureB.getUserData();

        if(dataA == null || dataB == null)
        {
            throw new IllegalStateException("Object data cannot find on contact");
        }

        if(PLAYER_SENSOR == (int)dataA)
        {

        }
        if(PLAYER_SENSOR == (int)dataB)
        {

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
