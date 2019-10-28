package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIText;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;

public class BattleEnemyHealthComponent extends GUIComponent {

    private GUIText healthText;
    private BattleEnemy enemy;
    private int health, healthTarget;

    public BattleEnemyHealthComponent(GUI gui, String name, GUIComponent parent, Vector2f size, BattleEnemy enemy) {
        super(gui, name, parent, size);
        this.enemy = enemy;
        health = enemy.getHealth();
        healthTarget = enemy.getHealth();

        healthText = new GUIText(gui, name + "_TEXT", this, new Vector2f(), 48, "");
        gui.setAbsolute();
    }

    @Override
    public void update(Scene scene) {
        healthText.setPos(Vector2f.addVectors(enemy.getPos(), new Vector2f(0, enemy.getSize().y)));

        healthTarget = enemy.getHealth();
        if(health < healthTarget)
            health++;
        if(health > healthTarget)
            health--;
        healthText.setText(health + "");
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
    }

    @Override
    public void setChildPos() {

    }
}
