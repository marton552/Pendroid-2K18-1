package com.hakkatoreinbukuma.spaceship.Screens.End;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyButton;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyLabel;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GlobalMusic;
import com.hakkatoreinbukuma.spaceship.Screens.Menu.MenuBackground;
import com.hakkatoreinbukuma.spaceship.Screens.Menu.MenuScreen;

public class EndStage extends MyStage {

    MyLabel dead;
    MyLabel info;
    MyButton back;


    public EndStage(Batch batch, final MyGdxGame game, float score, int wave) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        new MenuBackground(this);

        dead = new MyLabel("MEGHALTÁL", game.getLabelStyle());
        dead.setFontScale(1.5f, 1.5f);
        dead.getStyle().fontColor = Color.RED;
        dead.setPosition(getViewport().getWorldWidth() / 2 - dead.getWidth() / 2 - (dead.getWidth() / 2 / 2), getViewport().getWorldHeight() - dead.getHeight() - 50);

        addActor(dead);

        info = new MyLabel("Elért pontszám: "+(int)score+"\nElért szint: "+wave, game.getLabelStyle());
        info.setPosition(getViewport().getWorldWidth() / 2 - info.getWidth() / 2, getViewport().getWorldHeight() / 2 - info.getHeight() / 2);
        info.setAlignment(Align.center);

        addActor(info);

        back = new MyButton("Vissza a Menübe", game.getButtonStyle());
        back.setPosition(getViewport().getWorldWidth() / 2 - back.getWidth() / 2, 20);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GlobalMusic.stopMenuMusic();
                GlobalMusic.playPewSound();
                game.setScreen(new MenuScreen(game));
            }
        });
        addActor(back);


    }

    @Override
    public void init() {

    }
}
