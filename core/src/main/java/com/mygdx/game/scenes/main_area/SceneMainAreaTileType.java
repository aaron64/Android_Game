package com.mygdx.game.scenes.main_area;

public enum SceneMainAreaTileType {
    PATH("tile_normal"),
    PATH_ENEMY("tile_path_enemy"),
    ROOM("tile_room"),
    ROOM_ENEMY("tile_room_enemy"),
    SPAWN ("tile_spawn"),
    WATER ("tile_water"),
    CHEST ("tile_normal"),
    NONE ("tile_NONE");

    private String res;

    SceneMainAreaTileType(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }
}
