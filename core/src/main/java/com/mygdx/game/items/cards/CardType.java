package com.mygdx.game.items.cards;

public enum CardType {
    MAGIC("Magic"),
    MELEE("Melee"),
    BOW("Bow"),
    POTION("Potion"),
    SUPPORT("Support"),
    SPECIAL("Special"),
    THROWABLE("Throwable");

    private String str;
    CardType(String str) {
        this.str = str;
    }
}
