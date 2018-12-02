package com.mygdx.game.scenes;

import com.mygdx.game.entities.EntitySystem;
import com.mygdx.game.map.Map;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.RenderSystem;

public abstract class Scene {

    protected GestureUtil gestureHandler;
    protected EntitySystem entities;
    protected Map map;
    protected RenderSystem rs;

    public Scene() {
        entities = new EntitySystem();
        rs = new RenderSystem();
    }

    public abstract void update();
    public abstract void render();
    public abstract void dispose();
}
