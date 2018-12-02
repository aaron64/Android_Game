package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
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

    public SceneBattleGrid() {
        int[] mapWeights = {1,1,3,2,5};
        int mapI = MathUtil.getWeightedRandom(mapWeights);
        mapTexture = new Texture("battle_maps/map" + mapI + ".png");

        this.width = mapTexture.getWidth();
        this.height = mapTexture.getHeight();
        this.size = new Vector2(this.width, this.height);

        leftOffset = Window.percLeft(0.1f);
        rightOffset = Window.percRight(0.1f);
        topOffset = Window.percTop(0.4f);
        bottomOffset = Window.percBottom(0.1f);

        pixelWidth = rightOffset - leftOffset;
        pixelHeight = topOffset - bottomOffset;

        offsetVec =  new Vector2(leftOffset, bottomOffset);
        tileSize = new Vector2(pixelWidth / width, pixelHeight / height);

        tileGrid = new SceneBattleTile[width][height];
        gridSize = new Vector2(width, height);

        initializeGrid();
    }

    public void update() {
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
    }

    private void initializeGrid() {
        SceneBattleTileType[][] typeMap = ImageUtil.getBattleTileTypes(mapTexture);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                SceneBattleTileType type = typeMap[i][j];
                switch(type) {
                    case FRIENDLY:
                        tileGrid[i][j] = new SceneBattleTile(new Vector2(i,j), offsetVec, tileSize, this, type);
                        break;
                    case ENEMY:
                        tileGrid[i][j] = new SceneBattleTile(new Vector2(i,j), offsetVec, tileSize, this, type);
                        break;
                    case NEUTRAL:
                        tileGrid[i][j] = new SceneBattleTile(new Vector2(i,j), offsetVec, tileSize, this, type);
                        break;
                    case NONE:
                        tileGrid[i][j] = new SceneBattleTileNone(new Vector2(i,j), offsetVec, tileSize, this, type);
                        break;
                    default:
                        tileGrid[i][j] = new SceneBattleTileNone(new Vector2(i,j), offsetVec, tileSize, this, SceneBattleTileType.NONE);
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

    public SceneBattleTile getTile(int i, int j) {
        return tileGrid[i][j];
    }

    public SceneBattleTile getTile(Vector2 pos) {
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
