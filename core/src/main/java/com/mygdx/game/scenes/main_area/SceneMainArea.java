package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GUI.components.PlayerHealthComponent;
import com.mygdx.game.animation.PlayerDashAnimation;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.entities.main_area.MainAreaEntityComparator;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.lighting.SpotLight;
import com.mygdx.game.map.MapMainArea;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vec2f;

import java.util.Collections;

public class SceneMainArea extends Scene implements GestureHandler {

    private SceneMainAreaGrid grid;
    private Player player;

    public SceneMainArea() {
        super();
        rs.setCamera(new OrthographicCamera(Window.getWidth(), Window.getHeight()));
        rs.getCamera().zoom = 0.4f;

        grid = new SceneMainAreaGrid(this);

        gestureHandler = new GestureUtil(this);

        player = new Player(this, grid.getPlayerSpawn(), "player");
            lightEngine.addLight(new SpotLight(player, 1024, new Color(1, 1, 1, 0.6f)));

        entities.addEntity(player);

        map = new MapMainArea("bg1");

        PlayerHealthComponent playerHealthComponent = new PlayerHealthComponent(gui, "MAIN_HEALTH", gui.getNode(), new Vec2f(0.15f, 0.1f), player);
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
        lightEngine.render(rs);

        rs.beginFBO();
        rs.begin();
        rs.beginMainContent();
        rs.centerCameraOn(player);
        grid.render(rs);

        for(Entity e : entities.getList()) {
            e.render(rs);
        }
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
        rs.getCamera().zoom = Math.max(0.3f, Math.min(0.5f, rs.getCamera().zoom * (initialDistance/distance)));
    }

    @Override
    public void hold(float x, float y) {
        Vec2f touchVec = Vec2f.subtractVectors(new Vec2f(x, y), Window.getCenter());
        player.move(touchVec, this);
    }

    @Override
    public void stopHold(float x, float y) {
        player.resetSprite();
    }

    @Override
    public void doubleTap(float x, float y) {
        Vec2f direction = MathUtil.getUnitVector(Vec2f.subtractVectors(new Vec2f(x, y), Window.getCenter()));
        animationQueue.add(new PlayerDashAnimation(false, true, player, direction, 10, 200));
    }

    @Override
    public void tap(float x, float y) {
        Vec2f pos = rs.getWorldPos((int)x,(int)y);
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
