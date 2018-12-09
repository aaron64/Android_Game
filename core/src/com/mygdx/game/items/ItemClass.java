package com.mygdx.game.items;

public enum ItemClass {
    WEAK ("Weak", 0.25f),
    MINOR ("Minor", 0.5f),
    STANDARD ("", 1f),
    STRONG ("Strong", 1.5f),
    MAJOR ("Major", 2f),
    GRAND ("Grand", 2.5f);

    private String str;
    private float multiplier;

    ItemClass(String str, float multiplier) {
        this.str = str;
        this.multiplier = multiplier;
    }

    public String getStr() {
        return str;
    }

}
