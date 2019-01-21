package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.scenes.main_area.SceneMainAreaTileType;

public class ImageUtil {

    public static SceneMainAreaTileType[][] getMainAreaTileTypes(Texture image) {
        SceneMainAreaTileType[][] map = new SceneMainAreaTileType[image.getWidth()][image.getHeight()];

        image.getTextureData().prepare();
        Pixmap pixelMap = image.getTextureData().consumePixmap();

        final int white = Color.rgba8888(Color.WHITE);
        final int red = Color.rgba8888(Color.RED);
        final int green = Color.rgba8888(Color.GREEN);
        final int yellow = Color.rgba8888(Color.YELLOW);
        final int blue = Color.rgba8888(Color.BLUE);
        final int navy = Color.rgba8888(new Color(0,0,0.502f,1));
        final int black = Color.rgba8888(Color.BLACK);

        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int color = pixelMap.getPixel(i,j);

                if(color == white)
                    map[i][j] = SceneMainAreaTileType.NORMAL;
                else if(color == red)
                    map[i][j] = SceneMainAreaTileType.ENEMY;
                else if(color == yellow)
                    map[i][j] = SceneMainAreaTileType.CHEST;
                else if(color == blue)
                    map[i][j] = SceneMainAreaTileType.SPAWN;
                else if(color == navy)
                    map[i][j] = SceneMainAreaTileType.WATER;
                else if(color == green)
                    map[i][j] = SceneMainAreaTileType.DOOR;
                else
                    map[i][j] = SceneMainAreaTileType.NONE;
            }
        }

        return map;
    }

    public static SceneBattleTileType[][] getBattleTileTypes(Texture image) {
        SceneBattleTileType[][] map = new SceneBattleTileType[image.getWidth()][image.getHeight()];

        image.getTextureData().prepare();
        Pixmap pixelMap = image.getTextureData().consumePixmap();

        final int white = Color.rgba8888(Color.WHITE);
        final int red = Color.rgba8888(Color.RED);
        final int blue = Color.rgba8888(Color.BLUE);
        final int black = Color.rgba8888(Color.BLACK);
        final int green = Color.rgba8888(Color.GREEN);
        final int yellowFriendly = Color.rgba8888(new Color(1,1,0.502f,1));
        final int yellowEnemy = Color.rgba8888(new Color(1,0.502f,0,1));

        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int color = pixelMap.getPixel(i,j);

                if(color == white)
                    map[i][j] = SceneBattleTileType.NEUTRAL;
                else if(color == red)
                    map[i][j] = SceneBattleTileType.ENEMY;
                else if(color == blue)
                    map[i][j] = SceneBattleTileType.FRIENDLY;
                else if(color == black)
                    map[i][j] = SceneBattleTileType.NONE;
                else if(color == green)
                    map[i][j] = SceneBattleTileType.SPAWN;
                else if(color == yellowFriendly)
                    map[i][j] = SceneBattleTileType.OBJECT_FRIENDLY;
                else if(color == yellowEnemy)
                    map[i][j] = SceneBattleTileType.OBJECT_ENEMY;
                else
                    map[i][j] = SceneBattleTileType.NONE;
            }
        }

        return map;
    }
}
