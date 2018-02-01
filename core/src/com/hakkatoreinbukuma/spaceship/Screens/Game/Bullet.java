package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Bullet extends OneSpriteStaticActor {

    float velX;
    float velY;
    boolean friendly;
    GameStage stage;

    public Bullet(float velX, float velY, boolean friendly, float damage, GameStage stage) {
        super(Assets.manager.get(Assets.FULLWHITE_TEXTURE));

        this.velX = velX;
        this.velY = velY;
        this.friendly = friendly;
        this.damage = damage;
        this.stage = stage;

        setSize(4, 20);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        setPosition(getX() + velX, getY() + velY);

        if(friendly)
            if(getY() > stage.getViewport().getWorldHeight())
                stage.removeBulletFromWorld(this, true);
        else
            if(getY() < -getHeight())
                stage.removeBulletFromWorld(this, false);

    }

}
