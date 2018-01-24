package com.hakkatoreinbukuma.spaceship.Screens.Menu;

import com.badlogic.gdx.Gdx;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyScreen;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;

public class MenuScreen extends MyScreen{

    MenuStage menuStage;

    public MenuScreen(MyGdxGame game) {
        super(game);
        //setBackGroundColor(1, 1, 1);
        menuStage = new MenuStage(spriteBatch, game);
        Gdx.input.setInputProcessor(menuStage);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void init() {

    }
}
