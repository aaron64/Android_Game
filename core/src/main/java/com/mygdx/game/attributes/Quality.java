package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.Image;

public enum Quality {
    WEAK ("Weak", 0.25f, Image.GUI_ICON_WEAK),
    MINOR ("Minor", 0.5f, Image.GUI_ICON_MINOR),
    STANDARD ("", 1f),
    STRONG ("Strong", 1.5f, Image.GUI_ICON_STRONG),
    MAJOR ("Major", 2f, Image.GUI_ICON_MAJOR),
    GRAND ("Grand", 2.5f, Image.GUI_ICON_GRAND);

    private String str;
    private float multiplier;
    private Texture icon;

    Quality(String str, float multiplier, Texture icon) {
        this.str = str;
        this.multiplier = multiplier;
        this.icon = icon;
    }

    Quality(String str, float multiplier) {
        this(str, multiplier, null);
    }

    public String getStr() {
        return str;
    }
    public float getMultiplier() { return multiplier; }
    public Texture getIcon() {
        return icon;
    }

    public static Quality getQuality(int i) {
        switch(i) {
            case 0:
                return Quality.WEAK;
            case 1:
                return Quality.MINOR;
            case 2:
                return Quality.STANDARD;
            case 3:
                return Quality.STRONG;
            case 4:
                return Quality.MAJOR;
            case 5:
                return Quality.GRAND;
        }
        return Quality.STANDARD;
    }

    public static Quality getRandomQuality() {
        int r = (int) (Math.random() * 6);

        switch(r) {
            case 0:
                return Quality.GRAND;
            case 1:
                return Quality.MAJOR;
            case 2:
                return Quality.STRONG;
            case 3:
                return Quality.STANDARD;
            case 4:
                return Quality.MINOR;
            case 5:
                return Quality.WEAK;
        }
        return Quality.STANDARD;
    }
}
