package com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameStage;

/**
 * Created by tanulo on 2018. 02. 01..
 */

public class ArmorPowerup extends Powerup {
    public ArmorPowerup( GameStage stage) {
        super(Assets.manager.get(Assets.ARMOR_POWERUP), stage);

    }

    @Override
    public void onPickup() {
        super.onPickup();

        stage.ARMOR = 50;
    }
}
