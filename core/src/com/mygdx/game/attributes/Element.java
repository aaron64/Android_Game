package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Color;

public enum Element {
    FIRE ("Fire", new Color(0.827f, 0.298f, 0.267f, 1)),
    WATER ("Water", new Color(0.212f, 0.604f, 0.827f, 1)),
    WIND ("Wind", new Color(0.808f, 0.929f, 0.929f, 1)),
    POISON ("Poison", new Color(0.357f, 0.137f, 0.322f, 1)),
    SHOCK ("Shock", new Color(1f, 1f, 0.322f, 1)),
    GRASS ("Grass", new Color(0.247f, 0.729f, 0.322f, 1));

    private String str;
    private Color color;
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
    Element(String str, Color color) {
        this.str = str;
        this.color = color;
    }

    public String getStr() {
        return str;
    }
    public Color getColor() { return color; }
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
