package com.hakkatoreinbukuma.spaceship.MyBaseClasses.UI;


import com.badlogic.gdx.utils.Align;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.MyStage;
import com.hakkatoreinbukuma.spaceship.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class PictureWithComment {
    MyStage stage;

    public MyLabel label;

    public PictureWithComment(MyStage stage, float x, float y, OneSpriteStaticActor img, MyLabel label) {
        this.stage = stage;

        img.setSize(img.getWidth() / 4, img.getHeight() / 4);
        img.setPosition(x - 60, y);
        stage.addActor(img);

        this.label = label;

        label.setPosition(x, y - label.getHeight() + 20);
        label.setAlignment(Align.center);
        stage.addActor(label);


    }
}
