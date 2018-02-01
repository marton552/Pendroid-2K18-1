package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyScreen;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyButton;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI.MyLabel;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;
import com.hakkatoreinbukuma.spaceship.Screens.Menu.MenuScreen;

public class HUD extends MyStage{
    GameScreen screen;

    Pixmap hppixmap;
    OneSpriteStaticActor hpactor;
    Texture hptexture;

    Pixmap shieldpixmap;
    OneSpriteStaticActor shieldactor;
    Texture shieldtexture;

    Pixmap scorepixmap;
    OneSpriteStaticActor scoreactor;
    Texture scoretexture;

    Pixmap fadepixmap;
    Texture fadetexture;
    OneSpriteStaticActor fade;

    OneSpriteStaticActor menuBtn;
    MyLabel title;
    MyButton back;
    MyButton menu;


    public HUD(Batch batch, final MyGdxGame game, GameScreen screen) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        this.screen = screen;

        hppixmap = new Pixmap(200, 20, Pixmap.Format.RGBA8888);
        shieldpixmap = new Pixmap(200, 20, Pixmap.Format.RGBA8888);
        scorepixmap = new Pixmap(400, 15, Pixmap.Format.RGBA8888);

        hptexture = new Texture(hppixmap);
        shieldtexture = new Texture(shieldpixmap);
        scoretexture = new Texture(scorepixmap);

        hpactor = new OneSpriteStaticActor(hptexture);
        addActor(hpactor);
        hpactor.setPosition(10, 40);

        shieldactor = new OneSpriteStaticActor(shieldtexture);
        addActor(shieldactor);
        shieldactor.setPosition(10, 10);

        scoreactor = new OneSpriteStaticActor(scoretexture);
        addActor(scoreactor);
        scoreactor.setPosition(getViewport().getWorldWidth() / 2 - scoreactor.getWidth() / 2, getViewport().getWorldHeight() - 20);

        update(GameStage.HP, GameStage.ARMOR, GameStage.SCORE);

        //Menü


        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                if(keycode == Input.Keys.BACK){
                    startPauseMode();
                }

                return super.keyDown(event, keycode);
            }
        });

        menuBtn = new OneSpriteStaticActor(Assets.manager.get(Assets.MENU));
        menuBtn.setSize(32, 32);
        menuBtn.setPosition(getViewport().getWorldWidth() - menuBtn.getWidth()- 10, getViewport().getWorldHeight() - menuBtn.getHeight() - 10);
        addActor(menuBtn);

        menuBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Vissza");
                startPauseMode();
            }
        });



    }

    public void update(float hp, float shield, float score) {
        shieldpixmap.setColor(0, 0, 0, 0);
        shieldpixmap.fill();
        shieldpixmap.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        shieldpixmap.drawRectangle(0, 0, 200, 20);
        shieldpixmap.fillRectangle(0, 0, (int)(shield * 2), 20);

        hppixmap.setColor(0, 0, 0, 0);
        hppixmap.fill();
        hppixmap.setColor(1, 0, 0, 0.7f);
        hppixmap.drawRectangle(0, 0, 200, 20);
        hppixmap.fillRectangle(0, 0, (int)(hp * 2), 20);

        scorepixmap.setColor(0, 0, 0, 0);
        scorepixmap.fill();
        scorepixmap.setColor(1, 1, 0, 0.7f);
        scorepixmap.drawRectangle(0, 0, 400, 15);
        scorepixmap.fillRectangle(0, 0, (int)(score * 4), 15);

        hptexture = new Texture(hppixmap);
        hpactor.setTexture(hptexture);

        shieldtexture = new Texture(shieldpixmap);
        shieldactor.setTexture(shieldtexture);

        scoretexture = new Texture(scorepixmap);
        scoreactor.setTexture(scoretexture);
    }

    public void startPauseMode(){

        if(screen.paused == false){

            fadepixmap = new Pixmap((int) getViewport().getWorldWidth(), (int) getViewport().getWorldHeight(), Pixmap.Format.RGBA8888);

            fadepixmap.setColor(0, 0, 0, 0.7f);
            fadepixmap.fill();

            fadetexture = new Texture(fadepixmap);
            fade = new OneSpriteStaticActor(fadetexture);
            fade.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());

            title = new MyLabel("SZÜNET", game.getLabelStyle());
            title.setFontScale(1.5f, 1.5f);
            title.setPosition(getViewport().getWorldWidth() / 2 - title.getWidth() / 2 - (title.getWidth() / 2 / 2), getViewport().getWorldHeight() - title.getHeight() - 100);


            back = new MyButton("Vissza", game.getButtonStyle());
            back.setPosition(getViewport().getWorldWidth() / 2 - back.getWidth() / 2, getViewport().getWorldHeight() / 2 + 10);

            back.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    stopPauseMode();
                }
            });

            menu = new MyButton("Menü", game.getButtonStyle());
            menu.setPosition(getViewport().getWorldWidth() / 2 - back.getWidth() / 2, getViewport().getWorldHeight() / 2 - back.getHeight() - 10);


            menu.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    GlobalMusic.stopGameMusic();
                    game.setScreen(new MenuScreen(game));
                }
            });


            addActor(fade);
            addActor(title);
            addActor(back);
            addActor(menu);

            screen.paused = true;
        }
    }

    public void stopPauseMode() {
        if(screen.paused == true){

            getActors().removeValue(fade, false);
            getActors().removeValue(title, false);
            getActors().removeValue(back, false);
            getActors().removeValue(menu, false);

            screen.paused = false;
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        update(GameStage.HP, GameStage.ARMOR, GameStage.SCORE);
        super.act(delta);
    }
}
