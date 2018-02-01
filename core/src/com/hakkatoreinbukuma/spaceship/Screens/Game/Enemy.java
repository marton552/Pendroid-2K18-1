package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import java.util.Random;

public class Enemy extends OneSpriteStaticActor{
    GameStage stage;
    Random r = new Random();

    float speed;
    long hp;

    boolean canShoot;
    boolean doubleShot;
    int shootSpeed;
    boolean firstBullet = false;

    int tick = 0;

    public Enemy(Texture texture, float speed, long hp, boolean canShoot, boolean doubleShot, int shootSpeed, float damage, float ratio, GameStage stage) {
        super(texture);

        this.speed = speed;
        this.hp = hp;

        this.canShoot = canShoot;
        this.doubleShot = doubleShot;
        this.shootSpeed = shootSpeed;
        this.damage = damage;
        this.stage = stage;


        setSize(getWidth() / ratio, getHeight() / ratio);
        setY(stage.getViewport().getWorldHeight() + getHeight()  /2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY() - speed);

        if(canShoot) {
            tick++;
            if(tick >= shootSpeed) {
                if(doubleShot){
                    if(firstBullet == false){
                        stage.fireBullet(this, -5,false);
                        firstBullet = true;
                        System.out.println("Első lövés");
                    }
                    if (tick >= shootSpeed + 10){
                        System.out.println("Második lövés");

                        stage.fireBullet(this, -5,false);
                        firstBullet = false;
                        tick = 0;
                    }
                }else{
                    tick = 0;
                    stage.fireBullet(this, -5,false);
                }
            }
        }

        if(getY() + getHeight() < 0) {
            stage.removeEnemyFromWorld(this, false);
        }

    }
}
