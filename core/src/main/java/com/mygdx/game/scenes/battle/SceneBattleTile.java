package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class SceneBattleTile extends BattleEntity implements CooldownInterface {

    private BattleEntity entity;
    private SceneBattleTileType tileType;
    private boolean lightUp;

    private Cooldown lightUpCooldown;

    private Texture tileTexture, tileEndTexture;
    private Vector2f endPos;

    private static Texture lightUpOverlay = Image.getImage("entities/tiles/battle/tile_light_up");


    public SceneBattleTile(SceneBattle scene, SceneBattleGrid grid, Vector2i indexPos, Vector2f offset, Vector2i size, SceneBattleTileType tileType) {
        super(scene, grid, indexPos, "tiles/battle", tileType.getRes());

        this.tileType = tileType;
        setSize(size);
        entity = null;

        tileTexture = Image.getImage("entities/tiles/tile_basic");
        tileEndTexture = Image.getImage("entities/tiles/tile_end0");
        endPos = new Vector2f(getPos().x, getPos().y - getSize().y);

        lightUp = false;
        lightUpCooldown = new Cooldown(this, "LIGHT", false, 100);
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
    public void render(RenderSystem rs) {
        rs.draw(tileEndTexture, endPos, getSize());
        rs.draw(tileTexture, getPos(), getSize());
        super.render(rs);
        if(lightUp) {
            rs.setOverlayMode();
            rs.draw(lightUpOverlay, getPos(), getSize());
            rs.setNormalMode();
        }
    }

    public void renderEntity(RenderSystem rs) {
        if(entity != null) {
            entity.render(rs);
        }
    }

    public BattleEntity getEntity() {
        return entity;
    }

    public void setEntity(BattleEntity entity) {
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
