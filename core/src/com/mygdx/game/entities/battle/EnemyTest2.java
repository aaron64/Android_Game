package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Cooldown;

public class EnemyTest2 extends BattleEnemy {

    private Cooldown moveCooldown;
    int moves;

    public EnemyTest2(Vector2 pos, String name, SceneBattleGrid grid) {
        super(pos, name, grid);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};
        setSize(grid.getTile(0,0).getSize());
        cardStack.push(CardLoader.buildCard("Bow"));

        moveCooldown = new Cooldown(false, 80);
    }

    @Override
    public void update(Scene scene) {
        moveCooldown.update();

        if(moveCooldown.ready()) {
            SceneBattle sceneBattle = (SceneBattle)scene;
            moveCooldown.reset();
            jump(sceneBattle);
        }
    }

    public void jump(SceneBattle sceneBattle) {
        SceneBattleTile[] surroundings = sceneBattle.getGrid().getSurroundings(getIndexPos());

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

    public void shoot(SceneBattle sceneBattle) {
        cardStack.peek().use(sceneBattle, this);
    }
}
