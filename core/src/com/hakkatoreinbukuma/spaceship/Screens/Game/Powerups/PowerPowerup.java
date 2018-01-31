package com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameStage;

public class PowerPowerup extends Powerup {
    public PowerPowerup(GameStage stage) {
        super(Assets.manager.get(Assets.POWER_POWERUP), stage);
    }

    @Override
    public void onPickup() {
        super.onPickup();
        stage.ship.damage = stage.ship.damage + Math.round(stage.ship.damage *0.3);
        System.out.println(stage.ship.damage);

    }
}
