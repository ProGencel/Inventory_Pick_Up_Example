package com.myname.game.entities.player;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerRenderer {


    private float stateTime = 0;

    private Player player;

    public PlayerRenderer(Player player)
    {
        this.player = player;

    }

    public void render(float dt, SpriteBatch batch)
    {
        stateTime += dt;

        TextureRegion currentFrame = player.getPlayerController().getPlayerState().getAnimation().getKeyFrame(stateTime);

        float playerX = player.getBody().getPosition().x -
            (float) (currentFrame.getRegionWidth() / 2) * PPM;
        float playerY = player.getBody().getPosition().y;

        float playerWidth = currentFrame.getRegionWidth() * PPM;
        float playerHeight = currentFrame.getRegionHeight() * PPM;


        batch.draw(currentFrame,playerX,playerY,playerWidth,playerHeight);
    }

}
