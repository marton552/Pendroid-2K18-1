package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyScreen;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;

public class GameScreen extends MyScreen {

    GameStage gameStage;
    HUD hud;
    public boolean paused = false;

    public GameScreen(MyGdxGame game) {
        super(game);
        hud = new HUD(spriteBatch, game, this);
        gameStage = new GameStage(spriteBatch, game, hud);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud);
        inputMultiplexer.addProcessor(gameStage);

        Gdx.input.setInputProcessor(inputMultiplexer);

        GlobalMusic.stopMenuMusic();
        GlobalMusic.playGameMusic();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!paused)
            gameStage.act(delta);

        gameStage.draw();

        hud.act(delta);
        hud.draw();
    }

    @Override
    public void init() {

    }
}
