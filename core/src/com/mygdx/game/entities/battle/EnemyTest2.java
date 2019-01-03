package com.mygdx.game.entities.battle;


import com.mygdx.game.items.cards.CardBuilder;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.Vector2i;

import java.util.ArrayList;

public class EnemyTest2 extends BattleEnemy implements CooldownInterface {

    private Cooldown moveCooldown;
    private Cooldown attackCooldown;
    int moves;

    public EnemyTest2(SceneBattle scene, SceneBattleGrid grid, Vector2i pos, String name, int health) {
        super(scene, grid, pos, name, health);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};

        setSize(scene.getGrid().getTile(0,0).getSize());

        cardStack.push(CardBuilder.buildCard("Sword"));

        moveCooldown = new Cooldown(this, "MOVE", false, 80);
        attackCooldown = new Cooldown(this, "ATTACK", false, 100);
    }

    @Override
    public void update() {
        super.update();

        moveCooldown.update();
        attackCooldown.update();
    }

    public void jump() {
        ArrayList<SceneBattleTile> surroundings = scene.getGrid().getSurroundings(getIndexPos());

        boolean tileFound = false;
        int r = 0;
        while(!tileFound) {
            r = (int)(Math.random() * surroundings.size());
            if(tileAccepted(surroundings.get(r))) {
                tileFound = true;
            }
        }
        moveTo(surroundings.get(r).getIndexPos());
    }

    public void attack() {
        cardStack.peek().use(scene, this);
    }

    @Override
    public void trigger(String name) {
        if(name.equals("MOVE")) {
            moveCooldown.reset();
            jump();
        } else if(name.equals("ATTACK")) {
            Vector2i indexPos = getIndexPos();
            if(scene.getGrid().getTile(indexPos.x - 1, indexPos.y).getEntity() == scene.getPlayer()) {
                attack();
                attackCooldown.reset();
            }
        } else if(name.equals("LOCK")) {

        }
    }
}
