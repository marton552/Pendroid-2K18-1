package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import java.util.Random;

public class Enemy extends OneSpriteStaticActor{
    GameStage stage;
    Random r = new Random();

    int speed;
    int hp;

    boolean canShoot;
    int shootSpeed;

    int tick = 0;

    public Enemy(Texture texture, int speed, int hp, boolean canShoot, int shootSpeed, GameStage stage) {
        super(texture);

        this.speed = speed;
        this.hp = hp;

        this.canShoot = canShoot;
        this.shootSpeed = shootSpeed;

        this.stage = stage;


        setSize(getWidth() / 25, getHeight() / 25);
        setY(stage.getViewport().getWorldHeight() + getHeight()  /2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY() - speed);

        if(canShoot) {
            tick++;
            if(tick >= shootSpeed) {
                tick = 0;
                stage.fireBullet(this, -5,false);
            }
        }

        if(getY() + getHeight() < 0) {
            stage.removeEnemyFromWorld(this, false);
        }

    }
}
