package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.hakkatoreinbukuma.spaceship.Screens.End.EndScreen;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.ArmorPowerup;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.FireSpeedPowerup;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.FullArmorPowerup;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.HealPowerup;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.PowerPowerup;
import com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups.Powerup;

import java.util.ArrayList;
import java.util.Random;

public class GameStage extends MyStage {
    //ZENE
    //SDHIELD
    //POWERUP
    //ENEMY
    float ax = 0, ay = 0;
    boolean movable = false;

    OneSpriteStaticActor bg;
    public Spaceship ship;
    public static int wave = 1;
    public static float HP = 100;
    public static float ARMOR = 0;
    public static float SCORE = 0;

    public int kills = 0;
    public float finalScore = 0;

    Random r = new Random();

    int tick = 0;
    int nextWaveTick;


    ArrayList<Enemy> enemys = new ArrayList<Enemy>();

    ArrayList<Bullet> friendlyBullets = new ArrayList<Bullet>();
    ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();

    ArrayList<Powerup> powerups = new ArrayList<Powerup>();
    int[] powerupids = {-1, -1, -1};

    boolean powerupsShow = false;

    public HUD hud;


    public GameStage(Batch batch, final MyGdxGame game, final HUD hud) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BG_1));
        addActor(bg);

        this.hud = hud;

        finalScore = 0;
        wave = 1;
        HP = 100;
        ARMOR = 0;
        SCORE = 0;

        nextWave();

        ship = new Spaceship(this);
        ship.addBaseCollisionRectangleShape();
        ship.setPosition(getViewport().getWorldWidth() / 2 - ship.getWidth() / 2, 100);
        addActor(ship);

        //Test Powerup
        /*PowerPowerup powerup = new PowerPowerup(this);
        powerup.addBaseCollisionRectangleShape();
        powerup.setPosition(100, 100);
        powerups.add(powerup);
        addActor(powerup);*/


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
                ship.canShoot = true;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                movable = false;
                ship.canShoot = false;
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
                            SCORE = SCORE + 4.5f / (wave * 0.8f);
                            kills++;
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

                    float maradek = ARMOR - bullet.damage;
                    if(ARMOR > 0) {
                        ARMOR = ARMOR - (bullet.damage + (bullet.damage * 0.5f));
                    }

                    if (maradek < 0)
                        HP = HP + maradek;

                    removeBulletFromWorld(bullet,false);

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

                powerupids[0] = -1;
                powerupids[1] = -1;
                powerupids[2] = -1;


                for (int i = 0; i < powerups.size(); i++) {
                    getActors().removeValue(powerups.get(i), false);
                    //powerups.remove(i);
                }


                powerups.clear();

                wave++;
                finalScore += SCORE;
                SCORE = 0;
                powerupsShow = false;

            }
        }

        // Új Wave ha meghal mindenki. + ha az idő letellik.
        tick++;
        if(SCORE <= 99) {
            if (enemys.size() <= 0) {
                nextWave();
                tick = 0;
            }
            if (tick >= nextWaveTick) {
                tick = 0;
                nextWave();
            }
        }else{
            // Ha elérte a véget
            if(powerupsShow == false){
                System.out.println("Powerups");
                //ship.setPosition(getViewport().getWorldWidth() / 2 - ship.getWidth() / 2, 100);
                showPowerups();
            }

        }
        // Ha meghal akkor itt lesz az EndScreen
        if(HP <= 0){
            System.out.println("End Game");
            finalScore += SCORE;
            game.setScreen(new EndScreen(game, finalScore, wave, kills));
        }

        if((ship.getX() != ax || ship.getY() != ay) && movable) {
            float dx = ship.getX() - (ax - ship.getWidth() / 2),
                    dy = ship.getY() - (ay - ship.getHeight() / 2);
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

    public void showPowerups() {
        powerupsShow = true;
        for (int i = 0; i < 3; i++) {

            float xOff = 0;
            Powerup p = randomPowerup(i);
            p.addBaseCollisionRectangleShape();

            powerups.add(p);
            p.setY(getViewport().getWorldHeight() / 2 - p.getHeight() / 2 + 200);

            if(i == 0) xOff = - p.getWidth() / 2 - 100;
            if(i == 2) xOff = p.getWidth() / 2 + 100;

            p.setX(getViewport().getWorldWidth() / 2 - p.getWidth() / 2 + xOff);
            addActor(p);
        }
    }

    public Powerup randomPowerup(int index) {
        int rand = 0;
        boolean correct = false;

        while(correct == false){
            rand = r.nextInt(4);
            boolean correctT = true;

            for(int i = 0; i < 2; i++) {
                if(powerupids[i] == rand) correctT = false;
            }

            if(correctT == true)
                correct = true;
        }

        powerupids[index] = rand;

        if(rand == 0) {
            return new ArmorPowerup(this);
        }else if(rand == 1) {
            return new FireSpeedPowerup(this);
        }else if(rand == 2) {
            return new FullArmorPowerup(this);
        }else if(rand == 3) {
            return new HealPowerup(this);
        }else{
            return new PowerPowerup(this);
        }
    }

    public void nextWave(){
        //wave++;
        nextWaveTick = r.nextInt(200) + 200;
        int rEnemy = r.nextInt(4)+ 1; // 5 wave fajta
        //rEnemy = 2;
        if(rEnemy == 1){
            for (int i = 0; i < 5; i++) {
                Enemy temp = new Enemy(Assets.manager.get(Assets.ENEMY_1), 1, 10 * Math.round(wave *0.5), true, false, 90, 2, 25, this);
                enemys.add(temp);
                temp.setX(150 + 100 * (i + 1));

                temp.addBaseCollisionRectangleShape();
                addActor(temp);
            }
        }else if(rEnemy == 2) {
            for (int i = 0; i < 3; i++) {
                Enemy temp = new Enemy(Assets.manager.get(Assets.ENEMY_2), 0.7f, 15 * Math.round(wave *0.5), true, false, 200, 5, 6, this);
                enemys.add(temp);
                temp.setRotation(180);
                temp.setX(300 + 100 * (i + 1));

                temp.addBaseCollisionRectangleShape();
                addActor(temp);
            }
        }else if(rEnemy == 3) {
            for (int i = 0; i < 3; i++) {
                Enemy temp = new Enemy(Assets.manager.get(Assets.ENEMY_3), 5, 10 * Math.round(wave *0.5), false, false,0, 0, 5, this);
                enemys.add(temp);
                temp.setRotation(180);
                temp.setX(300 + 100 * (i + 1));

                temp.addBaseCollisionRectangleShape();
                addActor(temp);
            }
        }else if(rEnemy == 4) {
            for (int i = 0; i < 4; i++) {
                Enemy temp = new Enemy(Assets.manager.get(Assets.ENEMY_4), 0.8f, 15 * Math.round(wave *0.5), true, true, 150, 2, 2.5f, this);
                enemys.add(temp);
                temp.setRotation(180);
                temp.setX(200 * (i + 1));

                temp.addBaseCollisionRectangleShape();
                addActor(temp);
            }
        }
    }

    public void removeBulletFromWorld(Bullet bullet, boolean friendly) {
        getActors().removeValue(bullet, false);

        if(friendly) friendlyBullets.remove(bullet);
            else enemyBullets.remove(bullet);
    }



    public void fireBullet(OneSpriteStaticActor ship, int bulletSpeed, boolean friendly){
        for(int i = 0; i < 2; i++) {

            Bullet bullet = new Bullet(0, bulletSpeed, friendly, ship.damage, this);

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
