package com.hakkatoreinbukuma.spaceship.Screens.End;

import com.badlogic.gdx.Gdx;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyScreen;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GlobalMusic;

public class EndScreen extends MyScreen{

    EndStage endStage;

    public EndScreen(MyGdxGame game, float score, int wave) {
        super(game);

        endStage = new EndStage(spriteBatch, game, score, wave);

        Gdx.input.setInputProcessor(endStage);

        //GlobalMusic.stopGameMusic();
        GlobalMusic.startMenuMusic();
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        endStage.act(delta);
        endStage.draw();
    }
}
