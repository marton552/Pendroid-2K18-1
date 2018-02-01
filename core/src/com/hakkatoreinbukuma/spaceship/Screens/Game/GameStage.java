package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyButton;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyLabel;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.PowerPowerup;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.Powerup;

import java.util.ArrayList;

public class GameStage extends MyStage {

    float ax = 0, ay = 0;
    boolean movable = false;

    OneSpriteStaticActor bg;
    public Spaceship ship;
    int wave = 0;
    int HP = 100;
    int ARMOR = 0;
    int enemyBulletDamage = 1;

    ArrayList<Enemy> enemys = new ArrayList<Enemy>();

    ArrayList<Bullet> friendlyBullets = new ArrayList<Bullet>();
    ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();

    ArrayList<Powerup> powerups = new ArrayList<Powerup>();


    public GameStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BG_1));
        addActor(bg);

        nextWave();

        ship = new Spaceship(this);
        ship.addBaseCollisionRectangleShape();
        ship.setPosition(getViewport().getWorldWidth() / 2 - ship.getWidth() / 2, 100);
        addActor(ship);

        //Test Powerup
        PowerPowerup powerup = new PowerPowerup(this);
        powerup.addBaseCollisionRectangleShape();
        powerup.setPosition(100, 100);
        powerups.add(powerup);
        addActor(powerup);

        addListener(new DragListener(){

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                /*if(mouse){
                    lx = x;
                    ly = y;
                    mouse = false;
                } else {
                    //ship.setPosition(x - ship.getWidth() / 2, y - ship.getHeight() / 2);
                    ship.setPosition(lx - x, y - ship.getHeight() / 2);
                }*/
                ax = x;
                ay = y;
                super.drag(event, x, y, pointer);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //ship.setPosition(x - ship.getWidth() / 2, y - ship.getHeight() / 2);
                ax = x;
                ay = y;
                movable = true;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                movable = false;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);


        //Friendly Bullet collision with Enemy
        for (int i = 0; i < friendlyBullets.size(); i++) {
            if (!friendlyBullets.isEmpty()) {
                Bullet bullet = friendlyBullets.get(i);

                for (int e = 0; e < enemys.size(); e++) {
                    if (bullet.overlaps(enemys.get(e))) {

                        enemys.get(e).hp -= ship.damage;

                        getActors().removeValue(bullet, false);
                        friendlyBullets.remove(i);

                        if (enemys.get(e).hp < 0) { // Robbanás animation az Enemyre
                            removeEnemyFromWorld(enemys.get(e), true);
                        }
                    }
                }
            }
        }

        //Enemy Bullet collision with Player ship
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (!enemyBullets.isEmpty()) {
                Bullet bullet = enemyBullets.get(i);

                if (bullet.overlaps(ship)) {

                    removeBulletFromWorld(bullet,false);

                    HP = HP - enemyBulletDamage;
                }
            }
        }

        //Ship collision with enemy ships
        for (int e = 0; e < enemys.size(); e++) {
            if (ship.overlaps(enemys.get(e))) {

                removeEnemyFromWorld(enemys.get(e), true);
                HP = HP - 10;
            }
        }

        for (int p = 0; p < powerups.size(); p++) {
            if(ship.overlaps(powerups.get(p))){
                System.out.println("Powerup");
                powerups.get(p).onPickup();
                getActors().removeValue(powerups.get(p), false);
                powerups.remove(p);
            }
        }

        // Új Wave ha meghal mindenki (Ezt majd csere.)
        if (enemys.size() <= 0) nextWave();

        // Ha meghal akkor itt lesz az EndScreen
        if(HP <= 0){
            System.out.println("End Game");
        }

        if((ship.getX() != ax || ship.getY() != ay) && movable) {
            float dx = ship.getX() - (ax - ship.getWidth() / 2), dy = ship.getY() - (ay - ship.getHeight() / 2);
            float sensitivity = 5.0f;
            float aspect = getViewport().getWorldHeight() / getViewport().getWorldWidth();
            dx = Math.max(Math.min(dx, sensitivity), -sensitivity) * 1.0f/aspect;
            dy = Math.max(Math.min(dy, sensitivity), -sensitivity);
            ship.setPosition(ship.getX() - dx, ship.getY() - dy);
        }
    }

    public void removeEnemyFromWorld (Enemy enemy, boolean blowup) {

        if(blowup){
            Explosion explosion = new Explosion(this);
            explosion.setPosition(enemy.getX() + enemy.getWidth() / 2 - explosion.getWidth() / 2,
                                  enemy.getY() + enemy.getHeight() / 2 - explosion.getHeight() / 2);
            addActor(explosion);
        }

        getActors().removeValue(enemy, false);
        enemys.remove(enemys.indexOf(enemy));

    }

    public void nextWave(){
        wave++;
        if(wave == 1 || wave == 2){
            for (int i = 0; i < 5; i++) {
                Enemy temp = new Enemy(Assets.manager.get(Assets.ENEMY_1), 1, 15, true, 80, this);
                enemys.add(temp);
                temp.setX(150 + 100 * (i + 1));

                temp.addBaseCollisionRectangleShape();
                addActor(temp);
            }
        }
    }

    public void removeBulletFromWorld(Bullet bullet, boolean friendly) {
        getActors().removeValue(bullet, false);

        if(friendly) friendlyBullets.remove(friendlyBullets.indexOf(bullet));
            else enemyBullets.remove(enemyBullets.indexOf(bullet));
    }



    public void fireBullet(OneSpriteStaticActor ship, int bulletSpeed, boolean friendly){
        for(int i = 0; i < 2; i++) {

            Bullet bullet = new Bullet(0, bulletSpeed, friendly,this);

            if(friendly) friendlyBullets.add(bullet);
            else enemyBullets.add(bullet);

            if(i == 1) {
                bullet.setPosition(ship.getX() + ship.getWidth() - 10, ship.getY() + ship.getHeight());
            }else{
                bullet.setPosition(ship.getX() + 10, ship.getY() + ship.getHeight());
            }

            if (friendly == false) {
                bullet.setY(bullet.getY() - ship.getHeight());
            }

            bullet.addBaseCollisionRectangleShape();

            addActor(bullet);
        }
    }
}
