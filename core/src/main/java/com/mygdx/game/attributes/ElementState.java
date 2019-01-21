package com.mygdx.game.attributes;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.ElementStateFire;
import com.mygdx.game.attributes.ElementStateGrass;
import com.mygdx.game.attributes.ElementStatePoison;
import com.mygdx.game.attributes.ElementStateShock;
import com.mygdx.game.attributes.ElementStateWater;
import com.mygdx.game.attributes.ElementStateWind;
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
            case WIND:
                return new ElementStateWind(affected);
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
