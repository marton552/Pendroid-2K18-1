package com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameStage;

public class Powerup extends OneSpriteStaticActor{

    GameStage stage;
    public Powerup(Texture texture, GameStage stage) {
        super(texture);

        this.stage = stage;

        setSize(getWidth() / 6, getHeight() / 6);
    }

    public void onPickup() { }

    @Override
    public void act(float delta) {
        super.act(delta);

        /*setPosition(getX(), getY() - 5);

        if(getX() + getHeight() < 0) {
            stage.getActors().removeValue(this, false);
        }*/

    }
}
