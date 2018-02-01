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

    public HUD(Batch batch, MyGdxGame game) {
        super(new ExtendViewport(1024, 576, new OrthographicCamera(1024, 576)), batch, game);

        hppixmap = new Pixmap(200, 20, Pixmap.Format.RGBA8888);
        hppixmap.setColor(0, 0, 0, 0);
        hppixmap.fill();
        hppixmap.setColor(1, 0, 0, 1);
        hppixmap.drawRectangle(0, 0, 200, 20);

        shieldpixmap = new Pixmap(200, 20, Pixmap.Format.RGBA8888);
        shieldpixmap.setColor(0, 0, 0, 0);
        shieldpixmap.fill();
        shieldpixmap.setColor(0.5f, 0.5f, 0.5f, 1);
        shieldpixmap.drawRectangle(0, 0, 200, 20);

        hptexture = new Texture(hppixmap);

        shieldtexture = new Texture(shieldpixmap);

        hpactor = new OneSpriteStaticActor(hptexture);
        addActor(hpactor);
        hpactor.setPosition(10, 10);

        shieldactor = new OneSpriteStaticActor(shieldtexture);
        addActor(shieldactor);
        shieldactor.setPosition(10, 40);
    }

    public void update() {
        hptexture = new Texture(hppixmap);
        hpactor.setTexture(hptexture);

        shieldtexture = new Texture(shieldpixmap);
        shieldactor.setTexture(shieldtexture);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        update();
        super.act(delta);
    }
}
