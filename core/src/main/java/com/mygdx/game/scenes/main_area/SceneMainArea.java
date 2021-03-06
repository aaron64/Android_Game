package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.GUI.components.ButtonComponent;
import com.mygdx.game.GUI.components.GameMenuButtonComponent;
import com.mygdx.game.GUI.components.HealthComponent;
import com.mygdx.game.GUI.components.TitleComponent;
import com.mygdx.game.animation.PlayerDashAnimation;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.entities.main_area.MainAreaEntityComparator;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.map.MapMainArea;
import com.mygdx.game.map.MapTheme;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.MapNameGenerator;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.graphics.Window;

import java.util.Collections;

public class SceneMainArea extends Scene implements GestureHandler {

    private SceneMainAreaGrid grid;
    private Player player;

    public SceneMainArea() {
        super();
        rs.setCamera(new OrthographicCamera(Window.getWidth(), Window.getHeight()));
        rs.getCamera().zoom = 0.5f;
        grid = new SceneMainAreaGrid(this);
        gestureHandler = new GestureUtil(this);

        player = new Player(this, grid.getPlayerSpawn(), "player");

        gui.addComponent(new HealthComponent(gui, player));
        gui.addComponent(new TitleComponent(gui, MapNameGenerator.generateRandomName(100, MapTheme.FOREST)));

        ButtonComponent gameMenuButton = new GameMenuButtonComponent(gui, new Vector2f(0,0));
        gameMenuButton.setPos(new Vector2f(Window.getWidth() - gameMenuButton.getSize().w(), 0));
        gui.addComponent(gameMenuButton);

        entities.addEntity(player);

        map = new MapMainArea("bg1");
    }

    @Override
    public void update() {
        super.update();
        Collections.sort(entities.getList(), new MainAreaEntityComparator());

        map.update();
        gui.update(this);

        grid.update(this);

        for(Entity e : entities.getList()) {
            e.update();
        }
    }

    @Override
    public void render() {

        rs.centerCameraOn(player);
        rs.begin();

        map.render(rs);

        grid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }

        gui.render(rs);

        rs.end();
    }

    @Override
    public void renderInBackground() {
        rs.centerCameraOn(player);
        rs.begin();
        rs.setColor(0.5f, 0.5f, 0.5f, 1);

        map.render(rs);

        grid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }

        rs.setColor(1, 1, 1, 1);
        rs.end();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MainAreaEntity getEntityCollision(MainAreaEntity e0) {
        Rectangle collisionRect = e0.getCollisionRect();
        for(Entity e : entities.getList()) {

            if(e0 != e && ((MainAreaEntity)e).getCollisionRect().overlaps(collisionRect)) {
                return (MainAreaEntity) e;
            }
        }
        return null;
    }

    public MainAreaEntity getSolidEntityCollision(MainAreaEntity e0) {
        Rectangle collisionRect = e0.getCollisionRect();
        for(Entity e : entities.getList()) {

            if(e0 != e && ((MainAreaEntity)e).getCollisionRect().overlaps(collisionRect) && ((MainAreaEntity)e).isSolid()) {
                return (MainAreaEntity) e;
            }
        }
        return null;
    }

    @Override
    public void onPushed() {

    }

    @Override
    public void onPopped() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void dispose() {

    }

    public SceneMainAreaGrid getGrid() {
        return grid;
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void fling(float vx, float vy, int button) {

    }

    @Override
    public void zoom(float initialDistance, float distance) {
        rs.getCamera().zoom = Math.max(0.3f, Math.min(0.5f, initialDistance/distance));
    }

    @Override
    public void hold(float x, float y) {
        Vector2f touchVec = Vector2f.subtractVectors(new Vector2f(x, y), Window.getCenter());
        player.move(touchVec, this);
    }

    @Override
    public void stopHold(float x, float y) {
        player.resetSprite();
    }

    @Override
    public void doubleTap(float x, float y) {
        Vector2f direction = MathUtil.getUnitVector(Vector2f.subtractVectors(new Vector2f(x, y), Window.getCenter()));
        animationQueue.add(new PlayerDashAnimation(false, true, player, direction, 10, 200));
    }

    @Override
    public void tap(float x, float y) {
        Vector2f pos = rs.getWorldPos((int)x,(int)y);
        for(Entity e : entities.getList()) {
            if(e.getRect().contains(pos.x, pos.y)) {
                ((MainAreaEntity)e).clickOn();
            }
        }

        gui.tap(x, y);
    }

    public void resetInputs() {
        gestureHandler.reset();
    }
}
