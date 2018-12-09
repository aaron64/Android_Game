package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.main_area.EnemyStill;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.ImageUtil;
import com.mygdx.game.util.RenderSystem;

public class SceneMainAreaGrid {

    private int width, height;
    private Vector2 size;

    private float enemyChance;

    private SceneMainAreaTile[][] tileGrid;
    private Texture mapTexture;
    private Vector2 textureSize;

    public SceneMainAreaGrid(SceneMainArea scene) {
        int[] mapWeights = {1,1,3,2,5};
        int mapI = 0;//MathUtil.getWeightedRandom(mapWeights);
        mapTexture = new Texture("main_area_maps/room" + mapI + ".png");
        textureSize = new Vector2(mapTexture.getWidth(), mapTexture.getHeight());

        this.width = mapTexture.getWidth();
        this.height = mapTexture.getHeight();
        this.size = new Vector2(this.width, this.height);

        enemyChance = 0.2f;

        tileGrid = new SceneMainAreaTile[width][height];
        initializeGrid(scene);
    }

    public void update(Scene scene) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                tileGrid[i][j].update(scene);
            }
        }
    }

    public void render(RenderSystem rs) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                tileGrid[i][j].render(rs);
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
                        tileGrid[i][j] = new SceneMainAreaTile(new Vector2(i,j), "tiles", "tile_neutral", type);
                        break;
                    case SPAWN:
                        tileGrid[i][j] = new SceneMainAreaTile(new Vector2(i,j), "tiles", "tile_neutral", type);
                        if(Math.random() < enemyChance)
                            scene.addEntity(new EnemyStill(tileGrid[i][j].getPos(), "enemy"));
                        break;
                    case NONE:
                        tileGrid[i][j] = new SceneMainAreaTile(new Vector2(i,j), "tiles", "tile_friendly", type);
                        break;
                    default:
                        tileGrid[i][j] = new SceneMainAreaTile(new Vector2(i,j), "tiles", "tile_neutral", SceneMainAreaTileType.NONE);
                        break;
                }
            }
        }
    }

    public boolean isInBounds(Vector2 indexPos) {
        return indexPos.x >= 0 && indexPos.y >= 0 && indexPos.x < getWidth() && indexPos.y < getHeight();
    }

    public SceneMainAreaTile getTile(int i, int j) {
        return tileGrid[i][j];
    }

    public SceneMainAreaTile getTile(Vector2 pos) {
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

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}
