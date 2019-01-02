package com.mygdx.game.attributes;

public enum Element {
    FIRE ("Fire"),
    WATER ("Water"),
    WIND ("Wind"),
    POISON ("Poison"),
    SHOCK ("Shock"),
    GRASS ("Grass");

    private String str;
    private Element strength, weakness;

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
    Element(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
    public Element getStrength() {
        return strength;
    }
    public Element getWeakness() {
        return weakness;
    }

    public float getMultiplier(Element type) {
        if(type == this.weakness)
            return 0.5f;
        if(type == this.strength)
            return 2.0f;
        return 1.0f;
    }
}
