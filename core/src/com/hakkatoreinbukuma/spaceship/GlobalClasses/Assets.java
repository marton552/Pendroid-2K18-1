package com.hakkatoreinbukuma.spaceship.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g3d.Model;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;



	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "alegreyaregular.otf";
		fontParameter.fontParameters.size = 30;
		fontParameter.fontParameters.borderColor = Color.WHITE;
		fontParameter.fontParameters.borderWidth = 1;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);


	//public static final AssetDescriptor<Texture> AGYU_TEXTURE = new AssetDescriptor<Texture>("cannon/cannon5.png", Texture.class);
	//public static final AssetDescriptor<Texture> BG_TEXTURE = new AssetDescriptor<Texture>("back2.png", Texture.class);
	public static final AssetDescriptor<Texture> MENU_BG = new AssetDescriptor<Texture>("menu.png", Texture.class);

	public static final AssetDescriptor<TextureAtlas> TITLE_ATLAS = new AssetDescriptor<TextureAtlas>("atlasok/title.atlas", TextureAtlas.class);

	//Button
	public static final AssetDescriptor<Texture> BTN_BACK = new AssetDescriptor<Texture>("ui_textures/btn_back.png", Texture.class);
	public static final AssetDescriptor<Texture> BTN_HOVER = new AssetDescriptor<Texture>("ui_textures/btn_hover.png", Texture.class);

	public static final AssetDescriptor<Texture> WHITE_TEXTURE = new AssetDescriptor<Texture>("ui_textures/white.png", Texture.class);
	public static final AssetDescriptor<Texture> FULLWHITE_TEXTURE = new AssetDescriptor<Texture>("ui_textures/fullwhite.png", Texture.class);

	//Slider
	public static final AssetDescriptor<Texture> SLIDER_BG = new AssetDescriptor<Texture>("ui_textures/slider.png", Texture.class);
	public static final AssetDescriptor<Texture> SLIDER_KNOB = new AssetDescriptor<Texture>("ui_textures/sliderknob.png", Texture.class);
	public static final AssetDescriptor<Texture> SLIDER_KNOBH = new AssetDescriptor<Texture>("ui_textures/sliderknob2.png", Texture.class);

	public static final AssetDescriptor<Texture> LOGO = new AssetDescriptor<Texture>("logo.png", Texture.class);

	//Backgrounds
	public static final AssetDescriptor<Texture> BG_1 = new AssetDescriptor<Texture>("bg.jpg", Texture.class);


	//Anims
	public static final AssetDescriptor<TextureAtlas> EXPLOSION = new AssetDescriptor<TextureAtlas>("atlasok/explosion.atlas", TextureAtlas.class);


	//Spaceship
	public static final AssetDescriptor<Texture> SPACESHIP = new AssetDescriptor<Texture>("spaceship.png", Texture.class);

	//Enemy
	public static final AssetDescriptor<Texture> ENEMY_1 = new AssetDescriptor<Texture>("enemys/enemy1.png", Texture.class);
	public static final AssetDescriptor<Texture> ENEMY_2 = new AssetDescriptor<Texture>("enemys/enemy2.png", Texture.class);
	public static final AssetDescriptor<Texture> ENEMY_3 = new AssetDescriptor<Texture>("enemys/enemy3.png", Texture.class);
	public static final AssetDescriptor<Texture> ENEMY_4 = new AssetDescriptor<Texture>("enemys/enemy4.png", Texture.class);

	//Powerups
	public static final AssetDescriptor<Texture> POWER_POWERUP = new AssetDescriptor<Texture>("powerup/firepowerpick.png", Texture.class);
	public static final AssetDescriptor<Texture> FIRESPEED_POWERUP = new AssetDescriptor<Texture>("powerup/firespeedpick.png", Texture.class);
	public static final AssetDescriptor<Texture> HEAL_POWERUP = new AssetDescriptor<Texture>("powerup/docpick.png", Texture.class);
	public static final AssetDescriptor<Texture> ARMOR_POWERUP = new AssetDescriptor<Texture>("powerup/shieldpick.png", Texture.class);
	public static final AssetDescriptor<Texture> FULLARMOR_POWERUP = new AssetDescriptor<Texture>("powerup/shield2pick.png", Texture.class);


	//Music
	public static final AssetDescriptor<Music> MENU_MUSIC = new AssetDescriptor<Music>("music/menu.ogg", Music.class);
	public static final AssetDescriptor<Music> MUSIC_1 = new AssetDescriptor<Music>("music/1.ogg", Music.class);
	public static final AssetDescriptor<Music> MUSIC_2 = new AssetDescriptor<Music>("music/2.ogg", Music.class);
	public static final AssetDescriptor<Music> MUSIC_3 = new AssetDescriptor<Music>("music/3.ogg", Music.class);
	public static final AssetDescriptor<Sound> PEW_SOUND = new AssetDescriptor<Sound>("music/pew.ogg", Sound.class);



	public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		//manager.load(BG_TEXTURE);
		manager.load(MENU_BG);

		manager.load(TITLE_ATLAS);

		manager.load(BTN_BACK);
		manager.load(BTN_HOVER);

		manager.load(WHITE_TEXTURE);
		manager.load(FULLWHITE_TEXTURE);

		manager.load(SLIDER_BG);
		manager.load(SLIDER_KNOB);
		manager.load(SLIDER_KNOBH);

		manager.load(LOGO);

		manager.load(BG_1);

		manager.load(EXPLOSION);

		manager.load(SPACESHIP);

		manager.load(ENEMY_1);
		manager.load(ENEMY_2);
		manager.load(ENEMY_3);
		manager.load(ENEMY_4);

		manager.load(POWER_POWERUP);
		manager.load(FIRESPEED_POWERUP);
		manager.load(HEAL_POWERUP);
		manager.load(ARMOR_POWERUP);
		manager.load(FULLARMOR_POWERUP);

		manager.load(MENU_MUSIC);
		manager.load(MUSIC_1);
		manager.load(MUSIC_2);
		manager.load(MUSIC_3);
		manager.load(PEW_SOUND);

		manager.load(ALEGREYAREGULAR_FONT);

	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
