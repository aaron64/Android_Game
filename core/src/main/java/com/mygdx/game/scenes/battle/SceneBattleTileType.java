package com.mygdx.game.scenes.battle;

public enum SceneBattleTileType {
    FRIENDLY ("tile_friendly"),
    SPAWN ("tile_friendly"),
    ENEMY("tile_enemy"),
    NEUTRAL("tile_neutral"),
    OBJECT_FRIENDLY("tile_friendly"),
    OBJECT_ENEMY("tile_enemy"),
    NONE("tile_none");

    private String res;

    SceneBattleTileType(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }
}
