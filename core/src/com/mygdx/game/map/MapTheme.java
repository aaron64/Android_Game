package com.mygdx.game.map;

public enum MapTheme {
    FOREST("forest"),
    DUNGEON("dungeon"),
    CLOUD("cloud");

    private String str;

    MapTheme(String str) {
        this.str = str;
    }

    public String getRes() {
        return str;
    }
}
