package com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameStage;

public class HealPowerup extends Powerup
{
    public HealPowerup(GameStage stage) {
        super(Assets.manager.get(Assets.HEAL_POWERUP), stage);
    }

    @Override
    public void onPickup() {
        super.onPickup();
        stage.HP = 100;
    }
}
