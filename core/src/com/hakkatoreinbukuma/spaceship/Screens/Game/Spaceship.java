package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Spaceship extends OneSpriteStaticActor {

    public Spaceship() {
        super(Assets.manager.get(Assets.SPACESHIP));
        setSize(getWidth() / 4, getHeight() / 4);
    }

}
