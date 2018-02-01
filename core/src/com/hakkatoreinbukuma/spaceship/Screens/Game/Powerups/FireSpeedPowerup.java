package com.hakkatoreinbukuma.spaceship.Screens.Game.Powerups;

import com.badlogic.gdx.graphics.Texture;
import com.hakkatoreinbukuma.spaceship.GlobalClasses.Assets;
import com.hakkatoreinbukuma.spaceship.Screens.Game.GameStage;

public class FireSpeedPowerup extends Powerup{
    public FireSpeedPowerup(GameStage stage) {
        super(Assets.manager.get(Assets.FIRESPEED_POWERUP), stage);
    }

    @Override
    public void onPickup() {
        super.onPickup();
        stage.ship.attackSpeed = stage.ship.attackSpeed - Math.round(stage.ship.attackSpeed * 0.3);
    }
}
