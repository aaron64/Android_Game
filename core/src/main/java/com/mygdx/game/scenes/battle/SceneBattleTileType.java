package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Color;

public enum SceneBattleTileType {
    FRIENDLY ("tile_friendly", new Color(0.12f, 0.12f, 0.46f, 1)),
    SPAWN ("tile_friendly", new Color(0.12f, 0.12f, 0.46f, 1)),
    ENEMY("tile_enemy", new Color(0.45f, 0.12f, 0.12f, 1)),
    NEUTRAL("tile_neutral", new Color(1, 1, 1, 1)),
    OBJECT_FRIENDLY("tile_friendly", new Color(0.12f, 0.12f, 0.46f, 1)),
    OBJECT_ENEMY("tile_enemy", new Color(0.45f, 0.12f, 0.12f, 1)),
    NONE("tile_none", new Color(1, 1, 1, 1));

    private String res;
    private Color color;

    SceneBattleTileType(String res, Color color) {
        this.res = res;
        this.color = color;
    }

    public String getRes() {
        return res;
    }

    public Color getColor() { return color; }
}
