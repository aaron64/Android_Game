package com.mygdx.game.attributes;

import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.SpriteSheet;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.Vector2f;

public class ElementStateGrass extends ElementState implements CooldownInterface {

    private Cooldown timer;
    public ElementStateGrass(BattleLiving affected) {
        super(affected);
        timer = new Cooldown(this, "TIMER", false, 300);
        affected.setCanUseItem(false);
    }

    @Override
    public void update() {
        timer.update();
    }

    @Override
    public void render(RenderSystem rs, SpriteSheet spriteSheet, Vector2f pos) {
        rs.setShader(RenderSystem.grassOverlayShader);
        rs.beginShader();

        rs.setUniform1f("u_time", (float) Game.time);
        rs.setUniformTexture("u_texture_overlay", Image.getImage("misc/Grass_overlay"), 1);
        spriteSheet.render(rs, pos);

        rs.setShader(null);
    }

    @Override
    public void trigger(String name) {
        affected.setCanUseItem(true);
        affected.setElementState(null);
    }
}
