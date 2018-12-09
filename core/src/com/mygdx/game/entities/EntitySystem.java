package com.mygdx.game.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EntitySystem {

    private ArrayList<Entity> entityList;
    private ArrayList<Entity> removeList;

    public EntitySystem() {
        entityList = new ArrayList<Entity>();
        removeList = new ArrayList<Entity>();
    }

    public void update() {
        entityList.removeAll(removeList);
        removeList.clear();
    }

    public void addEntity(Entity e) {
        entityList.add(e);
    }

    public void removeEntity(Entity e) {
        removeList.add(e);
    }

    public ArrayList<Entity> getList() {
        return entityList;
    }
}
