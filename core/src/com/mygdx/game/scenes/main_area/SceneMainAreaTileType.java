package com.mygdx.game.scenes.main_area;

public enum SceneMainAreaTileType {
    NORMAL ("tile_normal"),
    SPAWN ("tile_spawn"),
    NONE ("tile_NONE");

    private String res;

    SceneMainAreaTileType(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }
}
