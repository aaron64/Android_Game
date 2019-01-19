package com.mygdx.game.items.shards;

import com.mygdx.game.attributes.Quality;
import com.mygdx.game.items.Item;
import com.mygdx.game.items.shards.ShardShape;
import com.mygdx.game.scenes.main_area.SceneMainArea;

public abstract class Shard extends Item {

    ShardShape shape;
    public Shard(String name, String folder, String description, Quality quality) {
        super(name, folder, description, quality);
    }

    public void onSet(SceneMainArea sceneMainArea) {}
    public void onUnset(SceneMainArea sceneMainArea) {}
    public void onBattleStart() {}
    public void onBattleFrame() {}
}
