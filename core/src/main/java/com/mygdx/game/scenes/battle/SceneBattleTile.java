package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class SceneBattleTile extends BattleEntity implements CooldownInterface {

    private Entity entity;
    private SceneBattleTileType tileType;
    private boolean lightUp;

    private com.mygdx.game.util.Cooldown lightUpCooldown;

    private static Texture lightUpOverlay = new Texture("entities/tiles/tile_light_up.png");


    public SceneBattleTile(SceneBattle scene, SceneBattleGrid grid, Vector2i indexPos, Vector2f offset, Vector2i size, SceneBattleTileType tileType) {
        super(scene, grid, indexPos, "tiles", tileType.getRes());

        this.tileType = tileType;
        setSize(size);
        entity = null;

        lightUp = false;
        lightUpCooldown = new com.mygdx.game.util.Cooldown(this, "LIGHT", false, 100);
    }

    @Override
    public void update() {
        if(entity != null) {
            entity.update();
        }

        if(lightUp) {
            lightUpCooldown.update();
            if(lightUpCooldown.ready()) {
                lightUp = false;
            }
        }
    }

    @Override
    public void render(com.mygdx.game.graphics.RenderSystem rs) {
        super.render(rs);
        if(lightUp) {
            rs.setOverlayMode();
            rs.draw(lightUpOverlay, getPos(), getSize());
            rs.setNormalMode();
        }
    }

    public void renderEntity(com.mygdx.game.graphics.RenderSystem rs) {
        if(entity != null) {
            entity.render(rs);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        if(this.entity != null && this.entity instanceof BattleEnemy) {
            scene.enemyKilled((BattleEnemy)this.entity);
        }
        this.entity = entity;
    }

    public void removeEntity() {
        entity = null;
    }

    public SceneBattleTileType getTileType() {
        return tileType;
    }

    public void setTileType(SceneBattleTileType tileType) {
        this.tileType = tileType;
    }

    public void lightUp(int duration) {
        lightUp = true;
        lightUpCooldown.setDuration(duration);
        lightUpCooldown.reset();
    }

    @Override
    public void trigger(String name) {

    }
}
