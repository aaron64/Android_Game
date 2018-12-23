package com.mygdx.game.scenes.battle;


import com.mygdx.game.GUI.components.BattleDeckComponent;
import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.EnemyTest;
import com.mygdx.game.entities.battle.EnemyTest2;
import com.mygdx.game.map.MapBattle;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

public class SceneBattle extends Scene implements GestureHandler {

    private SceneBattleGrid battleGrid;
    private BattlePlayer player;

    private int enemies;

    public SceneBattle() {
        super();

        gestureHandler = new GestureUtil(this);
        battleGrid = new SceneBattleGrid(this);

        enemies = 0;

        // Current scene is still main area during constructor
        addPlayer();

        map = new MapBattle(this,"bg1", battleGrid, player);

        gui.addComponent(new BattleDeckComponent(gui, player.getDeck()));

        new EnemyTest(this, battleGrid, new Vector2i(4, 2), "enemy", 40);
        new EnemyTest2(this, battleGrid, new Vector2i(3, 1), "enemy", 40);

        //battleGrid.getTile(5, 1).setEntity(new EnemyTest2(new Vector2(5, 1), "enemy", battleGrid));
    }

    @Override
    public void update() {
        super.update();
        map.update();

        battleGrid.update(this);

        for(Entity e : entities.getList()) {
            e.update();
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

    public void addPlayer() {
        int playerHealth = ((SceneMainArea)(Game.getCurrentScene())).getPlayer().getHealth();
        int playerMaxHealth = ((SceneMainArea)(Game.getCurrentScene())).getPlayer().getMaxHealth();

        player = new BattlePlayer(this, battleGrid, battleGrid.getPlayerSpawnCoords(), playerHealth, playerMaxHealth);
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
            player.useCard();
    }

    public void enemySpawned() {
        enemies++;
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
