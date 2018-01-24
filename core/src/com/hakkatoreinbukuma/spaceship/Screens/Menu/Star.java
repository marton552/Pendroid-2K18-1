package com.hakkatoreinbukuma.spaceship.Screens.Menu;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import java.util.Random;

public class Star extends OneSpriteStaticActor{

    float dirX;
    float dirY;
    int speed;

    float startX;
    float startY;

    float maxWidth;
    float maxHeight;

    int startTime;

    int tick = 0;
    Random r = new Random();

    MenuBackground mb;

    public Star(float x, float y, float dirX, float dirY, float maxWidth, float maxHeight, int startTime, MenuBackground mb) {
        super(Assets.manager.get(Assets.FULLWHITE_TEXTURE));
        //int border = 100;
        //x = x + 20 * -dirX;
        //y = y + 20 * dirY;
        setPosition(x, y);
        setSize(1, 1);
        setVisible(false);


        this.startX = x;
        this.startY = y;

        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;

        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        this.startTime = startTime;

        this.mb = mb;
    }


    @Override
    public void act(float delta) {

        if(tick >= startTime) {
            setVisible(true);
        }else tick++;

        if(getWidth() <= 8 && isVisible())
            setSize(getWidth() + 0.1f, getHeight() + 0.1f);

        setPosition(getX() + (dirX * getWidth()), getY() + (dirY * getHeight()));

        if(getX() >= maxWidth || getX() < 0 || getY() >= maxHeight || getY() < 0 || dirX == 0 || dirY == 0) {
            setPosition(startX, startY);
            setSize(1, 1);
            /*dirX  = r.nextInt((3 - -3) + 1) + -3;
            dirY = r.nextInt((3 - -3) + 1) + -3;
            dirX = r.nextInt((1 - -1) + 1) + -1;
            dirY = r.nextInt((1 - -1) + 1) + -1;*/

            dirX = mb.randomDir(0.1f, 3);
            dirY = mb.randomDir(0.1f, 3);

            tick = 0;
            setVisible(false);


        }

    }
}
