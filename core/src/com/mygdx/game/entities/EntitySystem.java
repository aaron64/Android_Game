package com.mygdx.game.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EntitySystem {

    private ArrayList<Entity> entityList;
    public EntitySystem() {
        entityList = new ArrayList<Entity>();
    }

    public void addEntity(Entity e) {
        entityList.add(e);
    }

    public void removeEntity(Entity e) {
        entityList.remove(e);
    }

    public ArrayList<Entity> getList() {
        return entityList;
    }
}
