package com.mygdx.game.attributes;

public enum Quality {
    WEAK ("Weak", 0.25f),
    MINOR ("Minor", 0.5f),
    STANDARD ("", 1f),
    STRONG ("Strong", 1.5f),
    MAJOR ("Major", 2f),
    GRAND ("Grand", 2.5f);

    private String str;
    private float multiplier;

    Quality(String str, float multiplier) {
        this.str = str;
        this.multiplier = multiplier;
    }

    public String getStr() {
        return str;
    }
    public float getMultiplier() { return multiplier; }

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
}
