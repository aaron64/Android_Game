package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.main_area.Chest;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.factories.EnemyFactory;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.ImageUtil;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;


public class SceneMainAreaGrid {

    private int width, height;
    private Vec2i size;

    private float enemyChance;

    private SceneMainAreaTile[][] tileGrid;
    private Vec2i tileSize;

    private Texture mapTexture;

    private Vec2f playerSpawn;

    public SceneMainAreaGrid(SceneMainArea scene) {
        int[] mapWeights = {1,1,3,2,5};
        int mapI = 0;//MathUtil.getWeightedRandom(mapWeights);
        mapTexture = new Texture("main_area_maps/testmap0.png");

        this.width = mapTexture.getWidth();
        this.height = mapTexture.getHeight();
        this.size = new Vec2i(this.width, this.height);

        Texture tileForSize = Image.getImage("entities/tiles/battle/tile_neutral");
        tileSize = new Vec2i(tileForSize.getWidth(), tileForSize.getHeight());
        tileSize.multiply(2);

        enemyChance = 0.05f;

        tileGrid = new SceneMainAreaTile[width][height];
        initializeGrid(scene);
    }

    public void update(Scene scene) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(tileGrid[i][j] != null)
                    tileGrid[i][j].update();
            }
        }
    }

    public void render(RenderSystem rs) {
        Vec2f BL = rs.getWorldPos(0,0);
        Vec2f TL = rs.getWorldPos(0, Window.getHeight());
        Vec2f TR = rs.getWorldPos(Window.getWidth(), Window.getHeight());
        Vec2f BR = rs.getWorldPos(Window.getWidth(),0);

        int leftOffset = (int)(BL.x/tileSize.w());
        int bottomOffset = (int)(TL.y/tileSize.h());

        int renderTileWidth = (int)((BR.x - BL.x)/tileSize.w())+2;
        int renderTileHeight = (int)((BL.y - TL.y)/tileSize.h())+4;

        for(int i = 0; i < renderTileWidth; i++) {
            for(int j = renderTileHeight; j >= 0; j--) {
                int ix = leftOffset + i;
                int jy = bottomOffset + j;
                if(isInBounds(new Vec2i(ix, jy)) && tileGrid[ix][jy] != null) {
                    tileGrid[ix][jy].render(rs);
                }
            }
        }
    }

    private void initializeGrid(SceneMainArea scene) {
        SceneMainAreaTileType[][] typeMap = ImageUtil.getMainAreaTileTypes(mapTexture);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                SceneMainAreaTileType type = typeMap[i][j];
                switch(type) {
                    case PATH:
                        tileGrid[i][j] = new SceneMainAreaTilePath(scene, new Vec2f(i,j), "tile_path_basic", type);
                        break;
                    case PATH_ENEMY:
                        tileGrid[i][j] = new SceneMainAreaTilePath(scene, new Vec2f(i,j), "tile_path_basic", type);
                        if(MathUtil.flipCoin(enemyChance))
                            EnemyFactory.newMainAreaEnemy(scene, new Vec2f(tileGrid[i][j].getPos()));
                        break;
                    case SPAWN:
                        tileGrid[i][j] = generateRoomTile(scene, i, j, typeMap);
                        playerSpawn = new Vec2f(tileGrid[i][j].getPos());
                        break;
                    case ROOM:
                        tileGrid[i][j] = generateRoomTile(scene, i, j, typeMap);
                        break;
                    case ROOM_ENEMY:
                        tileGrid[i][j] = generateRoomTile(scene, i, j, typeMap);
                        if(MathUtil.flipCoin(enemyChance))
                            EnemyFactory.newMainAreaEnemy(scene, new Vec2f(tileGrid[i][j].getPos()));
                        break;
                    case CHEST:
                        tileGrid[i][j] = generateRoomTile(scene, i, j, typeMap);
                        scene.addEntity(new Chest(scene, tileGrid[i][j].getPos(), tileGrid[i][j], Chest.ChestType.LOW));
                        break;
                    case WATER:
                        tileGrid[i][j] = new SceneMainAreaTilePathWater(scene, new Vec2f(i,j), type);
                        break;
                    case NONE:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private SceneMainAreaTileRoom generateRoomTile(SceneMainArea scene, int i, int j, SceneMainAreaTileType[][] typeMap) {

        String side = "C";

        SceneMainAreaTileType topLeft = typeMap[i - 1][j + 1];
        SceneMainAreaTileType top = typeMap[i][j + 1];
        SceneMainAreaTileType topRight = typeMap[i + 1][j + 1];
        SceneMainAreaTileType left = typeMap[i - 1][j];
        SceneMainAreaTileType right = typeMap[i + 1][j];
        SceneMainAreaTileType bottomLeft = typeMap[i - 1][j - 1];
        SceneMainAreaTileType bottom = typeMap[i][j - 1];
        SceneMainAreaTileType bottomRight = typeMap[i + 1][j - 1];

        if(!isRoom(left) && !isRoom(top)) {
            side = "TL";
        } else if(!isRoom(right) && !isRoom(top)) {
            side = "TR";
        } else if(!isRoom(left) && !isRoom(bottom)) {
            side = "BL";
        } else if(!isRoom(right) && !isRoom(bottom)) {
            side = "BR";
        } else if(!isRoom(left)) {
            side = "L";
        } else if(!isRoom(right)) {
            side = "R";
        } else if(!isRoom(top)) {
            side = "T";
        } else if(!isRoom(bottom)) {
            side = "B";
        }


        return new SceneMainAreaTileRoom(scene, new Vec2f(i, j), "tile_room" + side, typeMap[i][j]);
    }

    private boolean isRoom(SceneMainAreaTileType tileType) {
        return tileType == SceneMainAreaTileType.ROOM || tileType == SceneMainAreaTileType.ROOM_ENEMY || tileType == SceneMainAreaTileType.CHEST || tileType == SceneMainAreaTileType.SPAWN;
    }

    public boolean isInMap(MainAreaEntity e) {
        Rectangle rect = e.getCollisionRect();

        Vec2f pos = new Vec2f(rect.getX(), rect.getY());
        Vec2f size = new Vec2f(rect.getWidth(), rect.getHeight());

        Vec2i BL = Vec2i.divideVectors(pos, tileSize);
        Vec2i TL = Vec2i.divideVectors(new Vec2f(pos.x, pos.y + size.h()), tileSize);
        Vec2i TR = Vec2i.divideVectors(new Vec2f(pos.x + size.w(), pos.y + size.h()), tileSize);
        Vec2i BR = Vec2i.divideVectors(new Vec2f(pos.x + size.w(), pos.y), tileSize);

        return (getTile(BL) != null && getTile(TL) != null && getTile(TR) != null && getTile(BR) != null && e.getPos().x > 0 && e.getPos().y > 0);
    }


    public boolean isInBounds(Vec2i indexPos) {
        return indexPos.x >= 0 && indexPos.y >= 0 && indexPos.x < getWidth() && indexPos.y < getHeight();
    }

    public SceneMainAreaTile getTile(int i, int j) {
        return tileGrid[i][j];
    }

    public SceneMainAreaTile getTile(Vec2i pos) {
        return getTile((int)pos.x, (int)pos.y);
    }

    public SceneMainAreaTile getTile(Vec2f pos) { return getTile((int) pos.x/tileSize.x, (int) pos.y/tileSize.y); }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vec2i getSize() {
        return size;
    }

    public void setSize(Vec2i size) {
        this.size = size;
    }

    public Vec2f getPlayerSpawn() {
        return playerSpawn;
    }
}
