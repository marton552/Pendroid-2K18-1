package com.hakkatoreinbukuma.spaceship.Screens.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyButton;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyLabel;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;
import com.hakkatoreinbukuma.spaceship.Screens.About.AboutScreen;
import com.hakkatoreinbukuma.spaceship.Screens.End.EndScreen;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameScreen;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GlobalMusic;

public class MenuStage extends MyStage {

    private OneSpriteStaticActor logoActor = new OneSpriteStaticActor(Assets.manager.get(Assets.LOGO));

    float centerXLogo;
    float centerYLogo;

    public MenuStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        new MenuBackground(this);

        //logoActor.setSize(logoActor.getWidth(), logoActor.getHeight());
        centerXLogo = getViewport().getWorldWidth() / 2 - logoActor.getWidth() / 2;
        centerYLogo = getViewport().getWorldHeight() - logoActor.getHeight() + 50;

        logoActor.setPosition(centerXLogo, centerYLogo);


        MyButton playButton = new MyButton("Play", game.getButtonStyle());
        playButton.setPosition(getViewport().getWorldWidth() / 2 - playButton.getWidth() / 2, getViewport().getWorldHeight() - 360);

        playButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GlobalMusic.playPewSound();
                //game.setScreen(new GameScreen(game));
                game.setScreen(new EndScreen(game, 10000, 100));

            }
        });

        MyButton aboutButton = new MyButton("About", game.getButtonStyle());
        aboutButton.setPosition(getViewport().getWorldWidth() / 2 - playButton.getWidth() / 2, getViewport().getWorldHeight() - 420);

        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GlobalMusic.playPewSound();
                game.setScreen(new AboutScreen(game));
            }
        });

        MyButton quitButton = new MyButton("Quit", game.getButtonStyle());
        quitButton.setPosition(getViewport().getWorldWidth() / 2 - playButton.getWidth() / 2, getViewport().getWorldHeight() - 480);

        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        //addActor(bgActor);
        addActor(logoActor);
        addActor(playButton);
        addActor(aboutButton);
        addActor(quitButton);

    }

    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        super.act(delta);

        float x = centerXLogo + (Gdx.input.getAccelerometerY()) * delta * 40;
        if(x <= centerXLogo + 40 && x > centerXLogo - 40) {
            logoActor.setX(x);
        }

        float y = centerYLogo + (((Gdx.input.getAccelerometerX()) * delta * 20) * -1);
        if(y <= centerYLogo + 20 && y > centerYLogo - 20) {
            logoActor.setY(y);
        }
    }
}
