package com.mygdx.game.items.shards;

import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.scenes.main_area.SceneMainArea;

public class ShardHealth extends Shard {

    private int healthBoost;
    public ShardHealth(String name, String folder, String description, Quality quality, int healthBoost) {
        super(name, folder, description, quality);

        this.healthBoost = healthBoost;
    }

    @Override
    public void onSet(SceneMainArea sceneMainArea) {
        Player player = sceneMainArea.getPlayer();

        int newMaxHealth = player.getMaxHealth() + healthBoost;
        player.setMaxHealth(newMaxHealth);
        player.heal(healthBoost);
    }

    @Override
    public void onUnset(SceneMainArea sceneMainArea) {
        Player player = sceneMainArea.getPlayer();

        int newMaxHealth = player.getMaxHealth() - healthBoost;
        player.setMaxHealth(newMaxHealth);
        player.heal(-healthBoost);
    }
}
