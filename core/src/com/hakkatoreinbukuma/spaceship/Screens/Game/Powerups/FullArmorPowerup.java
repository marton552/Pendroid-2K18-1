package com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameStage;

public class FullArmorPowerup extends Powerup{
    public FullArmorPowerup(GameStage stage) {
        super(Assets.manager.get(Assets.FULLARMOR_POWERUP), stage);
    }

    @Override
    public void onPickup() {
        super.onPickup();

        stage.ARMOR = 100;
    }
}
