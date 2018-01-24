package com.hakkatoreinbukuma.spaceship.Screens.Menu;

import java.util.Random;

public class MenuBackground{
    MenuStage stage;
    Random r = new Random();

    public MenuBackground(MenuStage stage) {
        this.stage = stage;

        Star temp;
        for (int i = 0; i < 80; i++) {
            temp = new Star(stage.getViewport().getWorldWidth() / 2 ,
                            stage.getViewport().getWorldHeight() / 2,
                            randomDir(0.1f, 3),
                            randomDir(0.1f, 3),
                            stage.getViewport().getWorldWidth(),
                            stage.getViewport().getScreenHeight(),
                            this);

            stage.addActor(temp);
        }
    }

    public float randomDir(float min, float max){
        float number =  min + r.nextFloat() * (max - min);
        if(r.nextBoolean() == true) number *= -1;
        return number;
    }


}
