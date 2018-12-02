package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.battle.entities.BattleEntity;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.util.RenderSystem;

public class SceneBattleTile extends BattleEntity {

    private Entity entity;
    private SceneBattleTileType tileType;

    public SceneBattleTile(Vector2 indexPos, Vector2 offset, Vector2 size, SceneBattleGrid grid, SceneBattleTileType tileType) {
        super(indexPos, "tiles", tileType.getRes(), grid);

        this.tileType = tileType;

        entity = null;
    }

    @Override
    public void update() {
        if(entity != null) {
            entity.update();
        }
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
        if(entity != null) {
            entity.render(rs);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public SceneBattleTileType getTileType() {
        return tileType;
    }

    public void setTileType(SceneBattleTileType tileType) {
        this.tileType = tileType;
    }
}
