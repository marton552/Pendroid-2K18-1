package com.hakkatoreinbukuma.spaceship.Screens.About;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyButton;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyLabel;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.PictureWithComment;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;
import com.hakkatoreinbukuma.spaceship.Screens.Menu.MenuBackground;
import com.hakkatoreinbukuma.spaceship.Screens.Menu.MenuScreen;

public class AboutStage extends MyStage{

    MyLabel title;
    MyLabel info;
    MyButton back;

    public AboutStage(Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        new MenuBackground(this);

        title = new MyLabel("About", game.getLabelStyle());
        title.setFontScale(1.5f, 1.5f);
        title.setPosition(getViewport().getWorldWidth() / 2 - title.getWidth() / 2 - (title.getWidth() / 2 / 2), getViewport().getWorldHeight() - title.getHeight() - 30);

        addActor(title);

        info = new MyLabel("A játék célja, hogy minél több pontot és minél több űrhajót roobants fel.\n" +
                "A fenti csík mutatja, hogy milyen távol vagy a szint végig." +
                "\nMinden szint egyre nehezebb, úgyhogy figyelj oda." +
                "\nMinden pálya végén választhatsz egy fejelsztést amit a hajódra szerelhetsz." +
                "\nItt vannak, hogy melyik mire is jó:", game.getLabelStyle());
        info.setPosition(getViewport().getWorldWidth() / 2 - info.getWidth() / 2, getViewport().getWorldHeight() / 2 - info.getHeight() / 2 + 80);
        info.setAlignment(Align.center);

        addActor(info);

        new PictureWithComment(this, 180, 100, new OneSpriteStaticActor(Assets.manager.get(Assets.HEAL_POWERUP)), new MyLabel("100%\nÉleterő", game.getLabelStyle()));
        new PictureWithComment(this, 330, 100, new OneSpriteStaticActor(Assets.manager.get(Assets.POWER_POWERUP)), new MyLabel("+30%\nSebzés", game.getLabelStyle()));

        PictureWithComment speed = new PictureWithComment(this, 480, 100, new OneSpriteStaticActor(Assets.manager.get(Assets.FIRESPEED_POWERUP)), new MyLabel("+30%\nLövés\nGyorsaság", game.getLabelStyle()));
        speed.label.setX(speed.label.getX() - 30);

        new PictureWithComment(this, 630, 100, new OneSpriteStaticActor(Assets.manager.get(Assets.FULLARMOR_POWERUP)), new MyLabel("100%\nPáncél", game.getLabelStyle()));
        new PictureWithComment(this, 780, 100, new OneSpriteStaticActor(Assets.manager.get(Assets.ARMOR_POWERUP)), new MyLabel("50%\nPáncél", game.getLabelStyle()));


        back = new MyButton("Vissza", game.getButtonStyle());
        back.setPosition(getViewport().getWorldWidth() - back.getWidth() - 5, getViewport().getWorldHeight() - back.getHeight() - 20);

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MenuScreen(game));
            }
        });

        addActor(back);
    }

    @Override
    public void init() {

    }
}
