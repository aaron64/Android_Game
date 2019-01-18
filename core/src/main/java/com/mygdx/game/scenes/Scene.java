package com.mygdx.game.scenes;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.animation.AnimationQueue;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntitySystem;
import com.mygdx.game.map.Map;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.graphics.RenderSystem;

import java.util.ArrayList;

public abstract class Scene {

    protected com.mygdx.game.util.GestureUtil gestureHandler;
    protected com.mygdx.game.entities.EntitySystem entities;
    protected com.mygdx.game.map.Map map;
    protected com.mygdx.game.graphics.RenderSystem rs;
    protected com.mygdx.game.GUI.GUI gui;

    protected com.mygdx.game.animation.AnimationQueue animationQueue;

    public Scene() {
        entities = new com.mygdx.game.entities.EntitySystem();
        rs = new com.mygdx.game.graphics.RenderSystem();

        gui = new com.mygdx.game.GUI.GUI();

        animationQueue = new com.mygdx.game.animation.AnimationQueue();
    }

    public void update() {
        animationQueue.update(this);

        gestureHandler.update();
        entities.update();
    }

    public abstract void render();
    public void renderInBackground(){}
    public abstract void dispose();
    public abstract void onPushed();
    public abstract void onPopped();
    public abstract void onExit();

    public boolean isAnimationLocked() { return animationQueue.locked(); }

    public ArrayList<Entity> getEntities() {
        return entities.getList();
    }
    public void addEntity(Entity e) {
        entities.addEntity(e);
    }

    public void removeEntity(Entity e) {
        entities.removeEntity(e);
    }

    public GUI getGUI() {
        return gui;
    }
}
