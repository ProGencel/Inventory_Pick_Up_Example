package com.myname.game.entities.player;

import static com.myname.game.screens.gamescreen.utils.Constants.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerRenderer {

    private TextureAtlas atlas;
    private Animation<TextureRegion> walkRightAnim;
    private float stateTime = 0;

    private Player player;

    public PlayerRenderer(AssetManager assetManager, Player player)
    {
        this.player = player;
        atlas = assetManager.get("Sprites/Char.atlas");
        Array<TextureAtlas.AtlasRegion> frames = atlas.findRegions("idle_right");
        walkRightAnim = new Animation<>(0.4f, frames, Animation.PlayMode.LOOP);

    }

    public void render(float dt, SpriteBatch batch)
    {
        stateTime += dt;

        TextureRegion currentFrame = walkRightAnim.getKeyFrame(stateTime);

        float playerX = player.getBody().getPosition().x -
            (float) (currentFrame.getRegionWidth() / 2) * PPM;
        float playerY = player.getBody().getPosition().y;

        float playerWidth = currentFrame.getRegionWidth() * PPM;
        float playerHeight = currentFrame.getRegionHeight() * PPM;

        batch.draw(currentFrame,playerX,playerY,playerWidth,playerHeight);
    }

}
