package com.mygdx.game.entities.battle;


import com.mygdx.game.action.ActionLock;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
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

    public EnemyTest2(SceneBattle scene, SceneBattleTile tile, String name, int health) {
        super(scene, tile, name, health, null);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};

        setSize(scene.getGrid().getTile(0,0).getSize());

        cardStack.push(CardFactory.buildCard("Sword"));

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
        if(canUseItem()) {
            Card card = cardStack.peek();
            getActionQueue().add(new ActionLock(this, card.getInitialLock()));
            getActionQueue().add(new ActionUseCard(this, scene, card));
            getActionQueue().add(new ActionLock(this, card.getFinalLock()));
        }
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
