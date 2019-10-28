package com.mygdx.game.entities.battle.misc;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;

public class HitEffect extends BattleEntity {
    private int time, count;

    public HitEffect(SceneBattle scene, Vector2i indexPos) {
        super(scene, indexPos, "misc/hit_effect");

        scaleWidth(scene.getGrid().getTileSize().w());

        time = 8;
        count = 0;
    }

    @Override
    public void update() {
        count++;
        if(count > time) {
            scene.removeEntity(this);
        }
    }

    @Override
    public void render(RenderSystem rs) {
        rs.setShader(RenderSystem.hitEffectShader);
        rs.beginShader();
        rs.setUniform1f("u_time", time);
        rs.setUniform1f("u_count", count);
        super.render(rs);
        rs.setShader(null);
    }
}
