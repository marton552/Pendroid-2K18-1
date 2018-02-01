package com.hakkatoreinbukuma.spaceship.Screens.Game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;

public class HUD extends MyStage{
    Pixmap hppixmap;
    OneSpriteStaticActor hpactor;
    Texture hptexture;

    Pixmap shieldpixmap;
    OneSpriteStaticActor shieldactor;
    Texture shieldtexture;

    Pixmap scorepixmap;
    OneSpriteStaticActor scoreactor;
    Texture scoretexture;

    public HUD(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

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
    }

    public void update(int hp, int shield, int score) {
        shieldpixmap.setColor(0, 0, 0, 0);
        shieldpixmap.fill();
        shieldpixmap.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        shieldpixmap.drawRectangle(0, 0, 200, 20);
        shieldpixmap.fillRectangle(0, 0, shield * 2, 20);

        hppixmap.setColor(0, 0, 0, 0);
        hppixmap.fill();
        hppixmap.setColor(1, 0, 0, 0.7f);
        hppixmap.drawRectangle(0, 0, 200, 20);
        hppixmap.fillRectangle(0, 0, hp * 2, 20);

        scorepixmap.setColor(0, 0, 0, 0);
        scorepixmap.fill();
        scorepixmap.setColor(1, 1, 0, 0.7f);
        scorepixmap.drawRectangle(0, 0, 400, 15);
        scorepixmap.fillRectangle(0, 0, score * 4, 15);

        hptexture = new Texture(hppixmap);
        hpactor.setTexture(hptexture);

        shieldtexture = new Texture(shieldpixmap);
        shieldactor.setTexture(shieldtexture);

        scoretexture = new Texture(scorepixmap);
        scoreactor.setTexture(scoretexture);
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
