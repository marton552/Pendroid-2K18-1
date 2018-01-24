package com.hakkatoreinbukuma.spaceship.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyScreen;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.TitleScreen;
import com.hakkatoreinbukuma.spaceship.MyGdxGame;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(MyGdxGame game) {
		super(game);
    }
	BitmapFont bitmapFont = new BitmapFont();
	TextureAtlas atlas = new TextureAtlas("atlasok/loading.atlas");
	OneSpriteAnimatedActor loading = new OneSpriteAnimatedActor(atlas);
	{
		loading.setFps(60);
		loading.setLooping(true);
		loading.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	 @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		spriteBatch.begin();
		loading.draw(spriteBatch, 1f);
		loading.act(delta);
		bitmapFont.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+ Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)", 0, 0);
		spriteBatch.end();
		if (Assets.manager.update()) {
			Assets.afterLoaded();
			game.setScreen(new TitleScreen(game));
		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0f, 0f);
	}
}
