package com.hakkatoreinbukuma.spaceship.MyBaseClasses;

import com.badlogic.gdx.Gdx;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.Screens.Menu.MenuScreen;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyScreen;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;

public class TitleScreen extends MyScreen{
    private OneSpriteAnimatedActor title = new OneSpriteAnimatedActor(Assets.manager.get(Assets.TITLE_ATLAS));

    public TitleScreen(MyGdxGame game) {
        super(game);
        title.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        title.setLooping(false);
        title.setFps(20);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
            title.draw(spriteBatch, 1);
            title.act(delta);
        spriteBatch.end();
        if(!title.isRunning()){
            game.setScreen(new MenuScreen(game));
        }

    }

    @Override
    public void init() {

    }
}
