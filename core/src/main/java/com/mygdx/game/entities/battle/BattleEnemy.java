package com.mygdx.game.entities.battle;


import com.mygdx.game.GUI.components.BattleEnemyHealthComponent;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2f;

public abstract class BattleEnemy extends BattleLiving {

    protected BattleEnemyHealthComponent healthComponent;
    protected Element element;

    public BattleEnemy(SceneBattle scene, SceneBattleTile tile, String name, int health, Element element) {
        super(scene, tile, name, Facing.LEFT, health, health);
        healthComponent = new BattleEnemyHealthComponent(scene.getGUI(), name + "_HEALTH", scene.getGUI().getNode(), new Vector2f(0,0), this);
        this.element = element;

        scene.enemySpawned(this);
    }

    @Override
    public void update() {
        super.update();
        healthComponent.update(scene);
    }

    @Override
    public void die() {
        super.die();
        scene.getGUI().removeAbsolute(healthComponent);
        scene.getTile(getIndexPos()).removeEntity();

        scene.enemyKilled(this);
    }
}
