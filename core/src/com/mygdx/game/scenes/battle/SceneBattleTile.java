package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.RenderSystem;

public class SceneBattleTile extends BattleEntity {

    private Entity entity;
    private SceneBattleTileType tileType;
    private boolean lightUp;

    private Cooldown lightUpCooldown;

    private static Texture lightUpOverlay = new Texture("entities/tiles/tile_light_up.png");


    public SceneBattleTile(Vector2 indexPos, Vector2 offset, Vector2 size, SceneBattleGrid grid, SceneBattleTileType tileType) {
        super(indexPos, "tiles", tileType.getRes(), grid);

        this.tileType = tileType;
        setSize(size);
        entity = null;

        lightUp = false;
        lightUpCooldown = new Cooldown(false, 100);
    }

    @Override
    public void update(Scene scene) {
        if(entity != null) {
            entity.update(scene);
        }

        if(lightUp) {
            lightUpCooldown.update();
            if(lightUpCooldown.ready()) {
                lightUp = false;
            }
        }
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
        if(lightUp) {
            rs.setOverlayMode();
            rs.draw(lightUpOverlay, getPos(), getSize());
            rs.setNomarlMode();
        }
    }

    public void renderEntity(RenderSystem rs) {
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
}
