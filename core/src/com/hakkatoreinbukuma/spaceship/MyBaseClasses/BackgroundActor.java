package com.hakkatoreinbukuma.spaceship.MyBaseClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class BackgroundActor extends OneSpriteStaticActor {
    public BackgroundActor(Stage stage) {
        super(Assets.manager.get(Assets.MENU_BG));
        setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
    }

    @Override
    public void init() {
        super.init();
    }
}
