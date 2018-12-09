package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GUI.components.BattleDeckComponent;
import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.EnemyTest;
import com.mygdx.game.entities.battle.EnemyTest2;
import com.mygdx.game.map.MapBattle;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Window;

public class SceneBattle extends Scene implements GestureHandler {

    private SceneBattleGrid battleGrid;
    private BattlePlayer player;

    private int enemies;

    public SceneBattle() {
        super();

        //REMOVE THIS LATER
        enemies = 1;

        gestureHandler = new GestureUtil(this);
        battleGrid = new SceneBattleGrid();

        // Current scene is still main area during constructor
        int playerHealth = ((SceneMainArea)(Game.getCurrentScene())).getPlayer().getHealth();
        addPlayer(playerHealth);

        map = new MapBattle("bg1", battleGrid, player);

        gui.addComponent(new BattleDeckComponent(player.getDeck()));

        battleGrid.getTile(1,1).setEntity(player);
        battleGrid.getTile(4, 2).setEntity(new EnemyTest(new Vector2(4, 2), "enemy", battleGrid));
        //battleGrid.getTile(5, 1).setEntity(new EnemyTest2(new Vector2(5, 1), "enemy", battleGrid));
    }

    @Override
    public void update() {
        super.update();
        map.update();

        battleGrid.update(this);

        for(Entity e : entities.getList()) {
            e.update(this);
        }

        if(enemies <= 0) {
            returnToMainArea();
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

        gui.render(rs);

        rs.end();
    }

    public void returnToMainArea() {
        SceneMainArea sceneMainArea = (SceneMainArea) Game.getLastScene();
        sceneMainArea.getPlayer().setHealth(player.getHealth());
        Game.endScene();
    }

    public void addPlayer(int health) {
        player = new BattlePlayer(battleGrid.getPlayerSpawnCoords(), health, battleGrid);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void fling(float vx, float vy, int button) {
        player.move(vx, vy);
    }

    @Override
    public void zoom(float initialDistance, float distance) {

    }

    @Override
    public void hold(float x, float y) {

    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {
        if(x > Window.getWidth()/2)
            player.useCard(this);
    }

    public void enemyKilled() {
        enemies--;
    }

    public SceneBattleTile getTile(int i, int j) {
        return battleGrid.getTile(i,j);
    }

    public SceneBattleGrid getGrid() { return battleGrid; }

    public BattlePlayer getPlayer() {
        return player;
    }

    public void setPlayer(BattlePlayer player) {
        this.player = player;
    }
}
