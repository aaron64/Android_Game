package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.main_area.Chest;
import com.mygdx.game.entities.main_area.EnemyStill;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.ImageUtil;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

public class SceneMainAreaGrid {

    private int width, height;
    private Vector2i size;

    private float enemyChance;

    private SceneMainAreaTile[][] tileGrid;
    private Vector2i tileSize;

    private Texture mapTexture;

    private Vector2f playerSpawn;

    public SceneMainAreaGrid(SceneMainArea scene) {
        int[] mapWeights = {1,1,3,2,5};
        int mapI = 0;//MathUtil.getWeightedRandom(mapWeights);
        mapTexture = new Texture("main_area_maps/testmap.png");

        this.width = mapTexture.getWidth();
        this.height = mapTexture.getHeight();
        this.size = new Vector2i(this.width, this.height);

        Texture tileForSize = new Texture("entities/tiles/tile_neutral.png");
        tileSize = new Vector2i(tileForSize.getWidth(), tileForSize.getHeight());

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
        Vector2f BL = rs.getWorldPos(0,0);
        Vector2f TL = rs.getWorldPos(0, Window.getHeight());
        Vector2f TR = rs.getWorldPos(Window.getWidth(), Window.getHeight());
        Vector2f BR = rs.getWorldPos(Window.getWidth(),0);

        int leftOffset = (int)(BL.x/tileSize.x);
        int bottomOffset = (int)(TL.y/tileSize.y);

        int renderTileWidth = (int)((BR.x - BL.x)/tileSize.x)+2;
        int renderTileHeight = (int)((BL.y - TL.y)/tileSize.y)+2;

        for(int i = 0; i < renderTileWidth; i++) {
            for(int j = renderTileHeight-1; j >= 0; j--) {
                int ix = leftOffset + i;
                int jy = bottomOffset + j;
                if(isInBounds(new Vector2i(ix, jy)) && tileGrid[ix][jy] != null) {
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
                    case NORMAL:
                        tileGrid[i][j] = new SceneMainAreaTile(scene, new Vector2f(i,j), "tile_basic", type);
                        break;
                    case SPAWN:
                        tileGrid[i][j] = new SceneMainAreaTile(scene, new Vector2f(i,j), "tile_basic", type);
                        playerSpawn = new Vector2f(tileGrid[i][j].getPos());
                        break;
                    case ENEMY:
                        tileGrid[i][j] = new SceneMainAreaTile(scene, new Vector2f(i,j), "tile_basic", type);
                        if(Math.random() < enemyChance)
                            scene.addEntity(new EnemyStill(scene, tileGrid[i][j].getPos(), "enemy"));
                        break;
                    case CHEST:
                        tileGrid[i][j] = new SceneMainAreaTile(scene, new Vector2f(i,j), "tile_basic", type);
                        scene.addEntity(new Chest(scene, tileGrid[i][j].getPos(), tileGrid[i][j]));
                        break;
                    case DOOR:
                        tileGrid[i][j] = new SceneMainAreaTile(scene, new Vector2f(i,j), "tile_basic", type);
                        break;
                    case NONE:
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public boolean isInMap(MainAreaEntity e) {
        Rectangle rect = e.getCollisionRect();

        Vector2f pos = new Vector2f(rect.getX(), rect.getY());
        Vector2f size = new Vector2f(rect.getWidth(), rect.getHeight());

        Vector2i BL = Vector2i.divideVectors(pos, tileSize);
        Vector2i TL = Vector2i.divideVectors(new Vector2f(pos.x, pos.y + size.y), tileSize);
        Vector2i TR = Vector2i.divideVectors(new Vector2f(pos.x + size.x, pos.y + size.y), tileSize);
        Vector2i BR = Vector2i.divideVectors(new Vector2f(pos.x + size.x, pos.y), tileSize);

        return (getTile(BL) != null && getTile(TL) != null && getTile(TR) != null && getTile(BR) != null);
    }


    public boolean isInBounds(Vector2i indexPos) {
        return indexPos.x >= 0 && indexPos.y >= 0 && indexPos.x < getWidth() && indexPos.y < getHeight();
    }

    public SceneMainAreaTile getTile(int i, int j) {
        return tileGrid[i][j];
    }

    public SceneMainAreaTile getTile(Vector2i pos) {
        return getTile((int)pos.x, (int)pos.y);
    }

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

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }

    public Vector2f getPlayerSpawn() {
        return playerSpawn;
    }
}
