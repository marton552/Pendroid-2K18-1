package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Spaceship extends OneSpriteStaticActor {

    public long attackSpeed = 20;
    private int tick = 0;
    public boolean canShoot = false;

    private GameStage stage;

    public Spaceship(GameStage stage) {
        super(Assets.manager.get(Assets.SPACESHIP));

        this.stage = stage;

        damage = 5;

        setSize(getWidth() / 4, getHeight() / 4);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(canShoot) {
            tick++;

            if (tick >= attackSpeed) {
                stage.fireBullet(this, 5, true);
                tick = 0;
            }
        }


    }
}
