package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.ImageUtil;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Window;

public class SceneBattleGrid {

    private int width, height;
    private Vector2 size;

    private int leftOffset, rightOffset, topOffset, bottomOffset;
    private int pixelWidth, pixelHeight;
    private Vector2 offsetVec;
    private Vector2 tileSize;
    private Vector2 gridSize;

    private SceneBattleTile[][] tileGrid;
    private Texture mapTexture;

    private Vector2 playerSpawnCoords;

    public SceneBattleGrid(SceneBattle scene) {
        int[] mapWeights = {1,1,3,2,5,3};
        int mapI = MathUtil.getWeightedRandom(mapWeights);
        mapTexture = new Texture("battle_maps/map" + mapI + ".png");

        this.width = mapTexture.getWidth();
        this.height = mapTexture.getHeight();
        this.size = new Vector2(this.width, this.height);

        leftOffset = Window.percLeft(0.1f);
        rightOffset = Window.percRight(0.1f);
        topOffset = Window.percTop(0.35f);
        bottomOffset = Window.percBottom(0.15f);

        pixelWidth = rightOffset - leftOffset;
        pixelHeight = topOffset - bottomOffset;

        offsetVec =  new Vector2(leftOffset, bottomOffset);
        tileSize = new Vector2(pixelWidth / width, pixelHeight / height);

        tileGrid = new SceneBattleTile[width][height];
        gridSize = new Vector2(width, height);

        initializeGrid(scene);
    }

    public void update(Scene scene) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                tileGrid[i][j].update();
            }
        }
    }

    public void render(RenderSystem rs) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                tileGrid[i][j].render(rs);
            }
        }
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                tileGrid[i][j].renderEntity(rs);
            }
        }
    }

    private void initializeGrid(SceneBattle scene) {
        SceneBattleTileType[][] typeMap = ImageUtil.getBattleTileTypes(mapTexture);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                SceneBattleTileType type = typeMap[i][j];
                switch(type) {
                    case FRIENDLY:
                        tileGrid[i][j] = new SceneBattleTile(scene, this, new Vector2(i,j), offsetVec, tileSize,  type);
                        break;
                    case ENEMY:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vector2(i,j), offsetVec, tileSize, type);
                        break;
                    case NEUTRAL:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vector2(i,j), offsetVec, tileSize, type);
                        break;
                    case NONE:
                        tileGrid[i][j] = new SceneBattleTileNone(scene,this, new Vector2(i,j), offsetVec, tileSize, type);
                        break;
                    case SPAWN:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vector2(i,j), offsetVec, tileSize, SceneBattleTileType.FRIENDLY);
                        playerSpawnCoords = new Vector2(i,j);
                        break;
                    default:
                        tileGrid[i][j] = new SceneBattleTileNone(scene,this, new Vector2(i,j), offsetVec, tileSize, SceneBattleTileType.NONE);
                        break;
                }
            }
        }
    }

    public boolean isInBounds(Vector2 indexPos) {
        return indexPos.x >= 0 && indexPos.y >= 0 && indexPos.x < getWidth() && indexPos.y < getHeight();
    }

    public Vector2 getAbsoluteTilePosition(Vector2 pos) {
        return MathUtil.addVec(offsetVec, MathUtil.multiplyVec(pos, tileSize));
    }

    public Vector2 getIndexPosition(Vector2 absPos) {
        return MathUtil.divideVec(MathUtil.subVec(absPos, offsetVec), tileSize);
    }

    public SceneBattleTile getTile(int i, int j) {
        if(i >= 0 && i < getSize().x && j >= 0 && j < getSize().y)
            return tileGrid[i][j];
        return null;
    }

    public SceneBattleTile getTile(Vector2 pos) {
        return getTile((int)pos.x, (int)pos.y);
    }

    public SceneBattleTile[] getGroup(Vector2 p1, Vector2 p2) {
        return getGroup((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
    }

    public SceneBattleTile[] getSurroundings(Vector2 indexPos) {
        SceneBattleTile[] surround = new SceneBattleTile[8];

        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                int sp = (1 + i) * 2 + (1 + j);
                Vector2 v = new Vector2(i + indexPos.x, j + indexPos.y);
                if(isInBounds(v)) {
                    surround[sp] = getTile(v);
                } else {
                    surround[sp] = null;
                }
            }
        }

        return surround;
    }

    public SceneBattleTile[] getGroup(int x1, int y1, int x2, int y2) {
        int w = Math.abs(x2-x1) + 1;
        int h = Math.abs(y2-y1) + 1;

        int xStart = Math.min(x1, x2);
        int yStart = Math.min(y1, y2);

        SceneBattleTile[] group = new SceneBattleTile[w * h];
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < h; j++) {
                int tx = xStart + i;
                int ty = yStart + j;
                group[w * i + j] = getTile(tx, ty);
            }
        }

        return group;
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

    public Vector2 getPlayerSpawnCoords() {
        return playerSpawnCoords;
    }
}
