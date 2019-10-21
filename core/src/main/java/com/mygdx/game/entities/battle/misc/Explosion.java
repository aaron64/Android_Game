package com.mygdx.game.entities.battle.misc;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;

public class Explosion extends BattleEntity {

    private TimedSpriteSheet spriteSheet;
    private int time, count;

    public Explosion(SceneBattle scene, Vector2i indexPos) {
        super(scene, indexPos, "misc/explosion");
        setSize(new Vector2i(scene.getGrid().getTileSize()));
        getSize().y = getSize().x;

        time = 12;
        count = 0;

        spriteSheet = new TimedSpriteSheet(getImage(), 4, time / 4);
    }

    @Override
    public void update() {
        count++;
        if(count >= time)
            scene.removeEntity(this);
        spriteSheet.update();
    }

    @Override
    public void render(RenderSystem rs) {
        spriteSheet.render(rs, getPos(), getSize());
    }
}
