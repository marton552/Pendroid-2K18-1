package com.hakkatoreinbukuma.spaceship.Screens.Game;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;

import java.util.Random;

public class GlobalMusic {

    static Music menu;
    static Music game;
    static Sound pew = Assets.manager.get(Assets.PEW_SOUND);
    static Random r = new Random();
    static int lastsong = 4;

    public static void startMenuMusic() {
        menu = Assets.manager.get(Assets.MENU_MUSIC);
        menu.play();
        menu.setLooping(true);
    }

    public static void stopMenuMusic() { menu.stop(); }

    public static void playGameMusic() {
        playMusic();

        game.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                game.stop();
                playMusic();
            }
        });
    }

    public static void stopGameMhusic() {
        game.stop();
        game.dispose();
    }

    public static void playPewSound(){
        pew.play();
    }

    private static void playMusic(){
        int rand = r.nextInt(2);

        while(rand == lastsong) rand = r.nextInt(2);

        if(rand == 0){
            game = Assets.manager.get(Assets.MUSIC_1);
        } else if(rand == 1){
            game = Assets.manager.get(Assets.MUSIC_2);
        }else{
            game = Assets.manager.get(Assets.MUSIC_3);
        }

        System.out.println(rand);

        lastsong = rand;

        game.play();

    }
}
