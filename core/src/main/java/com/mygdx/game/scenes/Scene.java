package com.mygdx.game.scenes;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.animation.Animation;
import com.mygdx.game.animation.AnimationQueue;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntitySystem;
import com.mygdx.game.lighting.LightEngine;
import com.mygdx.game.map.Map;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.graphics.RenderSystem;

import java.util.ArrayList;

public abstract class Scene {

    protected GestureUtil gestureHandler;
    protected EntitySystem entities;
    protected Map map;
    protected RenderSystem rs;
    protected GUI gui;

    protected LightEngine lightEngine;

    protected AnimationQueue animationQueue;

    public Scene() {
        entities = new EntitySystem();
        rs = new RenderSystem();

        lightEngine = new LightEngine();
        gui = new GUI();

        animationQueue = new AnimationQueue();
    }

    public void update() {
        animationQueue.update(this);

        gestureHandler.update();
        entities.update();
    }

    public void addAnimation(Animation animation) {
        animationQueue.add(animation);
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

    public void buttonPress(String button) {}
}
