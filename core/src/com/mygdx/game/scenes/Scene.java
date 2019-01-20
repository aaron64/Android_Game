package com.mygdx.game.scenes;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.animation.AnimationQueue;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntitySystem;
import com.mygdx.game.entities.battle.misc.BattleArrow;
import com.mygdx.game.map.Map;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.RenderSystem;

public abstract class Scene {

    protected GestureUtil gestureHandler;
    protected EntitySystem entities;
    protected Map map;
    protected RenderSystem rs;
    protected GUI gui;

    protected AnimationQueue animationQueue;

    public Scene() {
        entities = new EntitySystem();
        rs = new RenderSystem();

        gui = new GUI();

        animationQueue = new AnimationQueue();
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
