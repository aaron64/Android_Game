package com.mygdx.game.attributes;

public enum ElementType {
    FIRE ("Fire"),
    WATER ("Water"),
    WIND ("Wind"),
    POISON ("Poison"),
    SHOCK ("Shock"),
    GRASS ("Grass");

    private String str;
    private ElementType strength, weakness;

    static {
        FIRE.strength = WIND;
        FIRE.weakness = WATER;

        WATER.strength = FIRE;
        WATER.weakness = SHOCK;

        WIND.strength = POISON;
        WIND.weakness = FIRE;

        POISON.strength = GRASS;
        POISON.weakness = WIND;

        SHOCK.strength = WATER;
        SHOCK.weakness = GRASS;

        GRASS.strength = SHOCK;
        GRASS.weakness = POISON;
    }
    ElementType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
    public ElementType getStrength() {
        return strength;
    }
    public ElementType getWeakness() {
        return weakness;
    }

    public float getMultiplier(ElementType type) {
        if(type == this.weakness)
            return 0.5f;
        if(type == this.strength)
            return 2.0f;
        return 1.0f;
    }
}
