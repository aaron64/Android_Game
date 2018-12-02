package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.battle.entities.BattlePlayer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.map.MapBattle;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;

public class SceneBattle extends Scene implements GestureHandler {

    private SceneBattleGrid battleGrid;
    private BattlePlayer player;

    public SceneBattle() {
        super();

        gestureHandler = new GestureUtil(this);
        battleGrid = new SceneBattleGrid();
        player = new BattlePlayer(new Vector2(1,1), "entities", "player", battleGrid);


        map = new MapBattle("bg1", battleGrid, player);


        battleGrid.getTile(1,1).setEntity(player);
    }

    @Override
    public void update() {
        map.update();

        battleGrid.update();

        for(Entity e : entities.getList()) {
            e.update();
        }
    }

    @Override
    public void render() {
        rs.begin();

        map.render(rs);

        battleGrid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }

        rs.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void fling(float vx, float vy, int button) {
        player.move(vx, vy);
    }
}
