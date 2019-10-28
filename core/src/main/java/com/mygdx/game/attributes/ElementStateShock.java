package com.mygdx.game.attributes;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.SpriteSheet;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.Vector2f;

public class ElementStateShock extends ElementState implements CooldownInterface {

    private Cooldown timer;
    public ElementStateShock(BattleLiving affected) {
        super(affected);

        timer = new Cooldown(this, "TIMER", false, 200);
        affected.lockFor(200);
    }

    @Override
    public void render(RenderSystem rs, SpriteSheet spriteSheet, Vector2f pos) {

    }

    @Override
    public void update() {
        timer.update();
    }

    @Override
    public void trigger(String name) {
        affected.setElementState(null);
    }
}
