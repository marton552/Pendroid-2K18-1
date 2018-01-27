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
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameScreen;

public class MenuStage extends MyStage {

    private OneSpriteStaticActor logoActor = new OneSpriteStaticActor(Assets.manager.get(Assets.EXAMPLE));

    float centerXLogo;
    float centerYLogo;

    public MenuStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        new MenuBackground(this);

        logoActor.setSize(logoActor.getWidth() / 2, logoActor.getHeight() / 2);
        logoActor.setPosition(getViewport().getWorldWidth() / 2 - logoActor.getWidth() / 2,
                              getViewport().getWorldHeight() - logoActor.getHeight() - 60);

        centerXLogo = getViewport().getWorldWidth() / 2 - logoActor.getWidth() / 2;
        centerYLogo = getViewport().getWorldHeight() - logoActor.getHeight() - 60;

        MyButton playButton = new MyButton("Play", game.getButtonStyle());
        playButton.setPosition(getViewport().getWorldWidth() / 2 - playButton.getWidth() / 2, getViewport().getWorldHeight() - 360);

        playButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        MyButton aboutButton = new MyButton("About", game.getButtonStyle());
        aboutButton.setPosition(getViewport().getWorldWidth() / 2 - playButton.getWidth() / 2, getViewport().getWorldHeight() - 420);

        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //game.setScreen(new AboutScreen(game));
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

        float x = logoActor.getX() + (Gdx.input.getAccelerometerY()) * delta * 40;
        if(x <= centerXLogo + 40 && x > centerXLogo - 40) {
            logoActor.setX(x);
        }

        float y = logoActor.getY() + (((Gdx.input.getAccelerometerX()) * delta * 20) * -1);
        if(y <= centerYLogo + 20 && y > centerYLogo - 20) {
            logoActor.setY(y);
        }
    }
}
