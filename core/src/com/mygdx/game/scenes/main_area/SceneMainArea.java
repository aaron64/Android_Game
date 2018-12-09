package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GUI.components.HealthComponent;
import com.mygdx.game.GUI.components.TitleComponent;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.map.MapMainArea;
import com.mygdx.game.map.MapTheme;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.MapNameGenerator;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Window;

public class SceneMainArea extends Scene implements GestureHandler {

    private SceneMainAreaGrid grid;
    private Player player;
    public SceneMainArea() {
        super();
        rs.setCamera(new OrthographicCamera(Window.getWidth(), Window.getHeight()));
        rs.getCamera().zoom = 0.6f;
        grid = new SceneMainAreaGrid(this);
        gestureHandler = new GestureUtil(this);

        player = new Player(new Vector2(100,100), "player");

        gui.addComponent(new HealthComponent(player));
        gui.addComponent(new TitleComponent(MapNameGenerator.generateRandomName(100, MapTheme.FOREST)));

        entities.addEntity(player);

        map = new MapMainArea("bg1");
    }

    @Override
    public void update() {
        super.update();
        map.update();
        gui.update(this);

        grid.update(this);

        for(Entity e : entities.getList()) {
            e.update(this);
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MainAreaEntity getCollision(MainAreaEntity e0) {
        Rectangle collisionRect = e0.getCollisionRect();
        for(Entity e : entities.getList()) {

            if(e0 != e && ((MainAreaEntity)e).getCollisionRect().overlaps(collisionRect)) {
                return (MainAreaEntity) e;
            }
        }
        return null;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void fling(float vx, float vy, int button) {

    }

    @Override
    public void zoom(float initialDistance, float distance) {
        rs.getCamera().zoom = Math.max(0.4f, Math.min(0.6f, initialDistance/distance));
        //rs.getCamera().zoom = Math.max(0.2f, Math.min(1.0f, ((initialDistance / distance)*1.9f) * rs.getCamera().zoom));
    }

    @Override
    public void hold(float x, float y) {
        Vector2 touchVec = MathUtil.subVec(new Vector2(x, y), Window.getCenter());
        player.move(touchVec);
    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {

    }
}
