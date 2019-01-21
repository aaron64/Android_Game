package com.mygdx.game.entities.main_area;

import com.mygdx.game.entities.Entity;

import java.util.Comparator;

public class MainAreaEntityComparator implements Comparator<Entity> {

    public int compare(Entity object1, Entity object2) {
        if(object1.getPos().y < object2.getPos().y) {
            return 1;
        }
        return -1;
    }
}
