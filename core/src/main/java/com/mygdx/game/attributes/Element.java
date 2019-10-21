package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Color;

public enum Element {
    FIRE ("Fire", new Color(0.827f, 0.298f, 0.267f, 1)),
    WATER ("Water", new Color(0.212f, 0.604f, 0.827f, 1)),
    POISON ("Poison", new Color(0.482f, 0.333f, 0.459f, 1)),
    SHOCK ("Shock", new Color(1f, 1f, 0.322f, 1)),
    GRASS ("Grass", new Color(0.57f, 0.8f, 0.2f, 1));

    private String str;
    private Color color;
    private Element[] strength, weakness;

    static {
        FIRE.strength = new Element[]{GRASS};
        FIRE.weakness = new Element[]{WATER};

        WATER.strength = new Element[]{FIRE, POISON};
        WATER.weakness = new Element[]{SHOCK, GRASS};

        POISON.strength = new Element[]{GRASS};
        POISON.weakness = new Element[]{WATER};

        SHOCK.strength = new Element[]{WATER};
        SHOCK.weakness = new Element[]{GRASS};

        GRASS.strength = new Element[]{WATER, SHOCK};
        GRASS.weakness = new Element[]{FIRE, POISON};
    }
    Element(String str, Color color) {
        this.str = str;
        this.color = color;
    }

    public String getStr() {
        return str;
    }
    public Color getColor() { return color; }
    public Element[] getStrength() {
        return strength;
    }
    public Element[] getWeakness() {
        return weakness;
    }

    public float getMultiplier(Element type) {
        for(Element element : weakness) {
            if (type == element)
                return 0.5f;
        }
        for(Element element : strength) {
            if (type == element)
                return 2.0f;
        }
        return 1.0f;
    }

    public static Element getRandomElement(boolean includeNone) {
        int r = (int) (Math.random() * 5);
        if(includeNone) {
            r = (int) (Math.random() * 6);
        }

        switch(r) {
            case 0:
                return Element.FIRE;
            case 1:
                return Element.WATER;
            case 2:
                return Element.POISON;
            case 3:
                return Element.SHOCK;
            case 4:
                return Element.GRASS;
            case 5:
                return null;
        }
        return Element.FIRE;
    }
}
