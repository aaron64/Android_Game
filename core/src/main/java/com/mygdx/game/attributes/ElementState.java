package com.mygdx.game.attributes;

import com.mygdx.game.entities.battle.BattleLiving;

public abstract class ElementState {

    protected BattleLiving affected;
    public ElementState(BattleLiving affected) {
        this.affected = affected;
    }

    public abstract void update();

    public static ElementState generateElementState(Element element, BattleLiving affected) {
        switch(element) {
            case FIRE:
                return new ElementStateFire(affected);
            case WATER:
                return new ElementStateWater(affected);
            case POISON:
                return new ElementStatePoison(affected);
            case SHOCK:
                return new ElementStateShock(affected);
            case GRASS:
                return new ElementStateGrass(affected);
        }
        return null;
    }
}
