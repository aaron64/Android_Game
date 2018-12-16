package com.mygdx.game.entities.battle;


import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.Vector2i;

public class EnemyTest2 extends BattleEnemy {

    private Cooldown moveCooldown;
    int moves;

    public EnemyTest2(SceneBattle scene, SceneBattleGrid grid, Vector2i pos, String name) {
        super(scene, grid, pos, name);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};
        setSize(scene.getGrid().getTile(0,0).getSize());
        cardStack.push(CardLoader.buildCard("Bow"));

        moveCooldown = new Cooldown(false, 80);
    }

    @Override
    public void update() {
        moveCooldown.update();

        if(moveCooldown.ready()) {
            moveCooldown.reset();
            jump();
        }
    }

    public void jump() {
        SceneBattleTile[] surroundings = scene.getGrid().getSurroundings(getIndexPos());

        boolean tileFound = false;
        int r = 0;
        while(!tileFound) {
            r = (int)(Math.random() * 8);
            if(tileAccepted(surroundings[r])) {
                tileFound = true;
            }
        }
        moveTo(surroundings[r].getIndexPos());
    }

    public void shoot() {
        cardStack.peek().use(scene, this);
    }
}
