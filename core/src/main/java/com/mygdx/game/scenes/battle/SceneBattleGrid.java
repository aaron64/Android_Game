package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.entities.battle.StillObject;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.items.cards.ThrowableSize;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.ImageUtil;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;
import com.mygdx.game.graphics.Window;

import java.util.ArrayList;

public class SceneBattleGrid {

    private int width, height;
    private Vec2i size;

    private int leftOffset, rightOffset, topOffset, bottomOffset;
    private int pixelWidth, pixelHeight;
    private Vec2f offsetVec;
    private Vec2i tileSize;

    private SceneBattleTile[][] tileGrid;
    private Texture mapTexture;

    private Vec2i playerSpawnCoords;

    public SceneBattleGrid(SceneBattle scene) {
        int[] mapWeights = {1,1,3,2,5,3, 1000, 5};
        int mapI = MathUtil.getWeightedRandom(mapWeights);
        mapTexture = Image.getImage("battle_maps/map" + mapI);

        this.width = mapTexture.getWidth();
        this.height = mapTexture.getHeight();
        this.size = new Vec2i(this.width, this.height);

        leftOffset = Window.percLeft(0.1f);
        rightOffset = Window.percRight(0.1f);
        topOffset = Window.percTop(0.35f);
        bottomOffset = Window.percBottom(0.15f);

        pixelWidth = rightOffset - leftOffset;
        pixelHeight = topOffset - bottomOffset;

        offsetVec =  new Vec2f(leftOffset, bottomOffset);
        tileSize = new Vec2i(pixelWidth / width, pixelHeight / height);

        tileGrid = new SceneBattleTile[width][height];

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
            for(int j = height-1; j >= 0; j--) {
                tileGrid[i][j].render(rs);
            }
        }
        for(int i = 0; i < width; i++) {
            for(int j = height-1; j >= 0; j--) {
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
                        tileGrid[i][j] = new SceneBattleTile(scene, this, new Vec2i(i,j), offsetVec, tileSize,  type);
                        break;
                    case ENEMY:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vec2i(i,j), offsetVec, tileSize, type);
                        break;
                    case NEUTRAL:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vec2i(i,j), offsetVec, tileSize, type);
                        break;
                    case NONE:
                        tileGrid[i][j] = new SceneBattleTileNone(scene,this, new Vec2i(i,j), offsetVec, tileSize, type);
                        break;
                    case SPAWN:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vec2i(i,j), offsetVec, tileSize, SceneBattleTileType.FRIENDLY);
                        playerSpawnCoords = new Vec2i(i,j);
                        break;
                    case OBJECT_FRIENDLY:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vec2i(i,j), offsetVec, tileSize, SceneBattleTileType.FRIENDLY);
                        new StillObject(scene, tileGrid[i][j], "pillar", 30);
                        break;
                    case OBJECT_ENEMY:
                        tileGrid[i][j] = new SceneBattleTile(scene,this, new Vec2i(i,j), offsetVec, tileSize, SceneBattleTileType.ENEMY);
                        new StillObject(scene, tileGrid[i][j], "pillar", 30);
                        break;
                    default:
                        tileGrid[i][j] = new SceneBattleTileNone(scene,this, new Vec2i(i,j), offsetVec, tileSize, SceneBattleTileType.NONE);
                        break;
                }
            }
        }
    }

    public boolean isInBounds(Vec2i indexPos) {
        return indexPos.x >= 0 && indexPos.y >= 0 && indexPos.x < getWidth() && indexPos.y < getHeight();
    }

    public boolean isFreeTile(Vec2i indexPos) {
        return isInBounds(indexPos) && getTile(indexPos).getEntity() == null && !getTile(indexPos).held();
    }

    public Vec2f getAbsoluteTilePosition(Vec2i indexPos) {
        return Vec2f.addVectors(offsetVec, Vec2f.multiplyVectors(indexPos, tileSize));
    }

    public Vec2i getIndexPosition(Vec2f absPos) {
        return Vec2i.divideVectors(Vec2f.subtractVectors(absPos, offsetVec), tileSize);
    }

    public SceneBattleTile getTile(int i, int j) {
        if(i >= 0 && i < getSize().x && j >= 0 && j < getSize().y)
            return tileGrid[i][j];
        return null;
    }

    public SceneBattleTile getTile(Vec2i pos) {
        return getTile((int)pos.x, (int)pos.y);
    }

    public SceneBattleTile[] getGroup(Vec2i p1, Vec2i p2) {
        return getGroup((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
    }

    public ArrayList<SceneBattleTile> getSurroundings(Vec2i indexPos) {
        ArrayList<SceneBattleTile> surround = new ArrayList<SceneBattleTile>();

        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                int sp = (1 + i) * 2 + (1 + j);
                Vec2i v = new Vec2i(i + indexPos.x, j + indexPos.y);
                if(isInBounds(v) && !(i == 0 && j == 0)) {
                    surround.add(getTile(v));
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

    public ArrayList<SceneBattleTile> getGroup(ThrowableSize size, Vec2i pos) {
        ArrayList<SceneBattleTile> list = new ArrayList<SceneBattleTile>();
        switch(size) {
            case SMALL: {
                SceneBattleTile tile = getTile(pos);
                if (tile != null)
                    list.add(tile);
                break;
            }
            case LARGE: {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (Math.abs(i) != 1 || Math.abs(j) != 1) {
                            SceneBattleTile tile = getTile(i + pos.x, j + pos.y);
                            if (tile != null)
                                list.add(tile);
                        }
                    }
                }
                break;
            }
            case HORIZONTAL:
                for(int i = -1; i < 2; i++) {
                    SceneBattleTile tile = getTile(i + pos.x, pos.y);
                    if (tile != null)
                        list.add(tile);
                }
                break;
            case VERTICAL:
                for(int i = -1; i < 2; i++) {
                    SceneBattleTile tile = getTile(pos.x, i + pos.y);
                    if (tile != null)
                        list.add(tile);
                }
                break;
        }
        return list;
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

    public Vec2i getSize() {
        return size;
    }

    public void setSize(Vec2i size) {
        this.size = size;
    }

    public Vec2i getTileSize() {
        return tileSize;
    }

    public Vec2i getPlayerSpawnCoords() {
        return playerSpawnCoords;
    }
}
