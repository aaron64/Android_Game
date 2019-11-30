package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.GUI.GUIVPanel;
import com.mygdx.game.GUI.components.BattleCurrentCardsComponent;
import com.mygdx.game.GUI.components.BattleGaugeComponent;
import com.mygdx.game.GUI.components.BattlePlayerHealthComponent;
import com.mygdx.game.Game;
import com.mygdx.game.animation.BattleEnemySpawnAnimation;
import com.mygdx.game.animation.GUIFadeInAnimation;
import com.mygdx.game.animation.OpenAnimation;
import com.mygdx.game.animation.ZoomAnimation;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEnemyMage;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.entities.battle.BattleEnemyTurret;
import com.mygdx.game.entities.battle.BattleEnemySwordsman;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.lighting.SpotLight;
import com.mygdx.game.map.MapBattle;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

import java.util.ArrayList;

public class SceneBattle extends Scene implements GestureHandler {

    private SceneBattleGrid battleGrid;
    private BattlePlayer player;

    private ArrayList<BattleEnemy> enemies;

    private SceneHandSelect sceneHandSelect;

    private BattleCurrentCardsComponent battleCurrentCards;
    private BattleGaugeComponent battleGaugeComponent;

    public SceneBattle() {
        super();
        rs.setCamera(new OrthographicCamera(Window.getWidth(), Window.getHeight()));
        rs.centerCameraOn(new Vec2f(Window.getCenter()));

        gestureHandler = new GestureUtil(this);
        enemies = new ArrayList<BattleEnemy>();

        battleGrid = new SceneBattleGrid(this);

        addPlayer();
        sceneHandSelect = new SceneHandSelect(player);
        lightEngine.addLight(new SpotLight(player, Window.getWidth() * 2, new Color(1f, 1f, 1f, 0.7f)));

        map = new MapBattle(this,"bg1", battleGrid, player);

        new BattleEnemyMage(this, battleGrid.getTile(new Vec2i(4, 2)), 40);
        new BattleEnemySwordsman(this, battleGrid.getTile(new Vec2i(3, 0)), 40);
        new BattleEnemyTurret(this, battleGrid.getTile(3, 2), 40);

        for(BattleEnemy enemy : enemies) {
            animationQueue.add(new BattleEnemySpawnAnimation(true, false, enemy, battleGrid.getTile(enemy.getIndexPos())));
        }

        animationQueue.add(new ZoomAnimation(true, false, 10, rs.getCamera().zoom, 1.2f, rs.getCamera()));
        animationQueue.add(new OpenAnimation(true, false, sceneHandSelect));

        GUIVPanel panel = new GUIVPanel(gui, "V_PANEL", gui.getNode(), new Vec2f(0.15f, 1));
        BattlePlayerHealthComponent playerHealthComponent = new BattlePlayerHealthComponent(gui, "MAIN_HEALTH", panel, new Vec2f(1, 0.1f), player);
        battleCurrentCards = new BattleCurrentCardsComponent(gui, panel, new Vec2f(1, 0.9f), player);

        battleGaugeComponent = new BattleGaugeComponent(gui, gui.getNode(), new Vec2f(0.5f, 0.1f));

        gui.getNode().setAlpha(0);

        rs.getCamera().zoom = 1f;
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
        lightEngine.render(rs);

        rs.beginFBO();
        rs.begin();

        rs.beginMainContent();

        battleGrid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }

        map.renderForeground(rs);

        rs.end();
        rs.endFBO();
        rs.restart();

        map.render(rs);

        rs.setShader(RenderSystem.lightingShader);
        rs.beginShader();
        rs.setUniformTexture("u_lightmap", lightEngine.getImage().getTexture(), 1);
        rs.drawFBO();
        rs.setShader(null);

        gui.render(rs);

        rs.end();
    }

    @Override
    public void renderInBackground() {
        lightEngine.render(rs);

        rs.beginFBO();
        rs.begin();

        rs.beginMainContent();

        battleGrid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }

        map.renderForeground(rs);

        rs.end();
        rs.endFBO();
        rs.restart();

        map.render(rs);

        rs.setShader(RenderSystem.lightingShader);
        rs.beginShader();
        rs.setUniformTexture("u_lightmap", lightEngine.getImage().getTexture(), 1);
        rs.drawFBO();
        rs.setShader(null);

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
        animationQueue.add(new ZoomAnimation(false, true, 10, rs.getCamera().zoom, 1.0f, rs.getCamera()));
        animationQueue.add(new GUIFadeInAnimation(20, gui.getNode()));
        battleCurrentCards.setCards();
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
            if (battleGaugeComponent.ready() && !animationQueue.inQueue("OPEN")) {
                animationQueue.add(new ZoomAnimation(true, false, 20, rs.getCamera().zoom, 1.2f, rs.getCamera()));
                animationQueue.add(new OpenAnimation(true, false, sceneHandSelect));
                battleCurrentCards.clear();
            }
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
            player.useCard(battleCurrentCards);
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

    public SceneBattleTile getTile(Vec2i indexPos) {
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
