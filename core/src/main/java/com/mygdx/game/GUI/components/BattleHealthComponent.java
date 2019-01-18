package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vector2f;

public class BattleHealthComponent extends GUIComponent {

    private BattleLiving living;

    private int renderHealth;
    private BitmapFont healthFont;
    public BattleHealthComponent(GUI gui, BattleLiving living ) {
        super(gui, "BATTLE_HEALTH");

        this.living = living;
        renderHealth = living.getHealth();

        healthFont = FontUtil.getFont(36);
    }

    @Override
    public void update(Scene scene) {
        if(renderHealth > living.getHealth()) {
            renderHealth--;
        } else if(renderHealth < living.getHealth()) {
            renderHealth++;
        }
    }

    @Override
    public void render(com.mygdx.game.graphics.RenderSystem rs) {
        rs.drawTextCentered(healthFont, ""+renderHealth, Vector2f.addVectors(living.getPos(), new Vector2f(living.getSize().x/2,living.getSize().y + 100)));
    }
}
