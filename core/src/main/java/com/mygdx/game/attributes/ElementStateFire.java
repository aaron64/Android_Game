package com.mygdx.game.attributes;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.SpriteSheet;
import com.mygdx.game.util.Vec2f;

public class ElementStateFire extends ElementState {

    private int count, time;
    public ElementStateFire(BattleLiving affected) {
        super(affected);

        count = 0;
        time = 500;
    }

    @Override
    public void render(RenderSystem rs, SpriteSheet spriteSheet, Vec2f pos) {

    }

    @Override
    public void update() {
        count++;

        if(count % 25 == 0) {
            affected.hit(1);
        }
    }
}
