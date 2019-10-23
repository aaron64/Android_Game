package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.GUI.components.BattlePlayerHealthComponent;
import com.mygdx.game.Game;
import com.mygdx.game.animation.BattleEnemySpawnAnimation;
import com.mygdx.game.animation.OpenAnimation;
import com.mygdx.game.animation.ZoomAnimation;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEnemyMage;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.entities.battle.EnemyTest;
import com.mygdx.game.entities.battle.EnemyTest2;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.map.MapBattle;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

import java.util.ArrayList;

public class SceneBattle extends Scene implements GestureHandler {

    private SceneBattleGrid battleGrid;
    private BattlePlayer player;

    private ArrayList<BattleEnemy> enemies;

    private SceneHandSelect sceneHandSelect;

    public SceneBattle() {
        super();
        rs.setCamera(new OrthographicCamera(Window.getWidth(), Window.getHeight()));
        rs.centerCameraOn(new Vector2f(Window.getCenter()));

        gestureHandler = new GestureUtil(this);
        enemies = new ArrayList<BattleEnemy>();

        battleGrid = new SceneBattleGrid(this);

        addPlayer();
        sceneHandSelect = new SceneHandSelect(player);

        map = new MapBattle(this,"bg1", battleGrid, player);

        new BattleEnemyMage(this, battleGrid.getTile(new Vector2i(4, 2)), 40);
        new EnemyTest2(this, battleGrid.getTile(new Vector2i(3, 0)), "enemy", 40);
        new EnemyTest(this, battleGrid.getTile(3, 2), "enemy", 40);

        for(BattleEnemy enemy : enemies) {
            animationQueue.add(new BattleEnemySpawnAnimation(true, false, enemy));
        }

        animationQueue.add(new ZoomAnimation(true, false, 10, rs.getCamera().zoom, 1.2f, rs.getCamera()));
        animationQueue.add(new OpenAnimation(true, false, sceneHandSelect));

        BattlePlayerHealthComponent playerHealthComponent = new BattlePlayerHealthComponent(gui, "MAIN_HEALTH", gui.getNode(), new Vector2f(0.15f, 0.1f), player);
    }

    @Override
    public void update() {
        super.update();
        map.update();

        if(!isAnimationLocked()) {
            gui.update(this);
            battleGrid.update(this);

            for(Entity e : entities.getList()) {
                e.update();
            }
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

        map.renderForeground(rs);

        gui.render(rs);

        rs.end();
    }

    @Override
    public void renderInBackground() {
        rs.begin();

        map.render(rs);

        battleGrid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }

        rs.end();
    }

    public void goToSelection() {
        Game.pushScene(sceneHandSelect);
    }

    @Override
    public void onPushed() {

    }

    @Override
    public void onPopped() {
        animationQueue.add(new ZoomAnimation(false, false, 10, rs.getCamera().zoom, 1.0f, rs.getCamera()));
    }

    @Override
    public void onExit() {
        SceneMainArea sceneMainArea = (SceneMainArea) Game.getLastScene();
        sceneMainArea.getPlayer().setHealth(player.getHealth());
    }

    public void addPlayer() {
        int playerHealth = ((SceneMainArea)(Game.getCurrentScene())).getPlayer().getHealth();
        int playerMaxHealth = ((SceneMainArea)(Game.getCurrentScene())).getPlayer().getMaxHealth();

        player = new BattlePlayer(this, battleGrid.getTile(battleGrid.getPlayerSpawnCoords()), playerHealth, playerMaxHealth);
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
        if(distance/initialDistance <= 0.5f) {
            /*if (((BattleSelectionBarComponent) gui.getComponent("SELECTION_BAR")).ready() && !animationQueue.inQueue("OPEN")) {
                animationQueue.add(new ZoomAnimation(true, true, 10, rs.getCamera().zoom, 1.2f, rs.getCamera()));
                animationQueue.add(new OpenAnimation(true, false, sceneHandSelect));
            }*/
        }
    }

    @Override
    public void hold(float x, float y) {

    }

    @Override
    public void stopHold(float x, float y) {

    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {
        if(x > Window.getWidth()/2)
            player.useCard();
        else
            player.useSecondary();
    }

    public void enemySpawned(BattleEnemy enemy) {
        enemies.add(enemy);
    }

    public void enemyKilled(BattleEnemy enemy) {
        enemies.remove(enemy);
        if(enemies.isEmpty()) {
            Game.endScene();
        }
    }

    public SceneBattleTile getTile(int i, int j) {
        return battleGrid.getTile(i,j);
    }

    public SceneBattleTile getTile(Vector2i indexPos) {
        return battleGrid.getTile(indexPos);
    }

    public SceneBattleGrid getGrid() { return battleGrid; }

    public BattlePlayer getPlayer() {
        return player;
    }

    public void setPlayer(BattlePlayer player) {
        this.player = player;
    }
}
