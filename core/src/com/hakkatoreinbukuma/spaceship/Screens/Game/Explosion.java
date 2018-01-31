package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Explosion extends OneSpriteAnimatedActor{
    GameStage stage;

    public Explosion(GameStage stage) {
        super(Assets.manager.get(Assets.EXPLOSION));

        this.stage = stage;
        looping = false;
        setSize(getWidth() - 100, getHeight() - 100);
    }

    @Override
    public void stop() {
        super.stop();

        stage.getActors().removeValue(this, false);
    }
}
