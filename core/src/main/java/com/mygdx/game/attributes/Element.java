package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.SpriteSheet;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public enum Element {
    FIRE ("Fire",
            new Color(0.922f, 0.506f, 0.569f, 1),
            new Color(0.831f, 0.541f, 0.639f, 1),
            new Color(0.729f, 0.502f, 0.604f, 1)),

    WATER ("Water",
            new Color(0.341f, 0.682f, 0.831f, 1),
    	    new Color(0.369f, 0.561f, 0.722f, 1),
    	    new Color(0.290f, 0.412f, 0.569f, 1)),

    POISON ("Poison",
            new Color(0.596f, 0.557f, 0.804f, 1),
    	    new Color(0.459f, 0.475f, 0.643f, 1),
    	    new Color(0.361f, 0.384f, 0.482f, 1)),

    SHOCK ("Shock",
            new Color(1.000f, 0.925f, 0.651f, 1),
    	    new Color(0.878f, 0.831f, 0.651f, 1),
    	    new Color(0.761f, 0.753f, 0.608f, 1)),

    GRASS ("Grass",
            new Color(0.690f, 0.851f, 0.690f, 1),
    	    new Color(0.639f, 0.749f, 0.655f, 1),
    	    new Color(0.459f, 0.561f, 0.490f, 1));

    private String str;
    private Color highlight, mid, shadow;
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
    Element(String str, Color highlight, Color mid, Color shadow) {
        this.str = str;
        this.highlight = highlight;
        this.mid = mid;
        this.shadow = shadow;
    }

    public String getStr() {
        return str;
    }

    public Color getHighlight() { return highlight; }
    public Color getMid() { return mid; }
    public Color getShadow() { return shadow; }

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

    public static void drawTextureWithOverlay(RenderSystem rs, Texture texture, Texture overlay, Vector2f pos, Vector2i size, Element element) {
        rs.draw(texture, pos, size);
        if(element != null) {
            rs.setShader(RenderSystem.elementShader);
            rs.beginShader();
            rs.setUniform4f("u_highlight", element.highlight);
            rs.setUniform4f("u_mid", element.mid);
            rs.setUniform4f("u_shadow", element.shadow);
            rs.draw(overlay, pos, size);
            rs.setShader(null);
        }
    }

    public static void drawTextureWithOverlay(RenderSystem rs, SpriteSheet texture, SpriteSheet overlay, Vector2f pos, Vector2i size, Element element) {
        texture.render(rs, pos, size);
        if(element != null) {
            rs.setShader(RenderSystem.elementShader);
            rs.beginShader();
            rs.setUniform4f("u_highlight", element.highlight);
            rs.setUniform4f("u_mid", element.mid);
            rs.setUniform4f("u_shadow", element.shadow);
            overlay.render(rs, pos, size);
            rs.setShader(null);
        }
    }
}
