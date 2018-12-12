package com.mygdx.game.attributes;

public enum QualityType {
    WEAK ("Weak", 0.25f),
    MINOR ("Minor", 0.5f),
    STANDARD ("", 1f),
    STRONG ("Strong", 1.5f),
    MAJOR ("Major", 2f),
    GRAND ("Grand", 2.5f);

    private String str;
    private float multiplier;

    QualityType(String str, float multiplier) {
        this.str = str;
        this.multiplier = multiplier;
    }

    public String getStr() {
        return str;
    }
    public float getMultiplier() { return multiplier; }

}
