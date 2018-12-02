package com.mygdx.game.scenes.battle;

public enum SceneBattleTileType {
    FRIENDLY ("tile_friendly"),
    ENEMY("tile_enemy"),
    NEUTRAL("tile_neutral"),
    NONE("tile_none");

    private String res;

    SceneBattleTileType(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }
}
