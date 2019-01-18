package com.mygdx.game.items.shards;

import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.items.shards.Shard;
import com.mygdx.game.scenes.main_area.SceneMainArea;

public class ShardHealth extends Shard {

    private int healthBoost;
    public ShardHealth(String name, String folder, String description, Quality quality, int healthBoost) {
        super(name, folder, description, quality);

        this.healthBoost = healthBoost;
    }

    @Override
    public void onSet(com.mygdx.game.scenes.main_area.SceneMainArea sceneMainArea) {
        com.mygdx.game.entities.main_area.Player player = sceneMainArea.getPlayer();

        int newMaxHealth = player.getMaxHealth() + healthBoost;
        player.setMaxHealth(newMaxHealth);
        player.heal(healthBoost);
    }

    @Override
    public void onUnset(com.mygdx.game.scenes.main_area.SceneMainArea sceneMainArea) {
        com.mygdx.game.entities.main_area.Player player = sceneMainArea.getPlayer();

        int newMaxHealth = player.getMaxHealth() - healthBoost;
        player.setMaxHealth(newMaxHealth);
        player.heal(-healthBoost);
    }
}
