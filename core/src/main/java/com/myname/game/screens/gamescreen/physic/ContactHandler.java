package com.myname.game.screens.gamescreen.physic;

import static com.myname.game.screens.gamescreen.inventory.Inventory.box;
import static com.myname.game.screens.gamescreen.utils.Constants.*;
import static com.myname.game.screens.gamescreen.utils.StaticMethods.*;

import com.badlogic.gdx.physics.box2d.*;
import com.myname.game.events.EventManager;
import com.myname.game.events.itemEvent.ItemEvent;

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

        if(isTheyTheLookingFixtures(PLAYER_SENSOR,STATIC_DATA,dataA,dataB))
        {
            EventManager.newItemEvent(new ItemEvent(box));
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
