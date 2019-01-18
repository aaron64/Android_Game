package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTileType;

public class ImageUtil {

    public static com.mygdx.game.scenes.main_area.SceneMainAreaTileType[][] getMainAreaTileTypes(Texture image) {
        com.mygdx.game.scenes.main_area.SceneMainAreaTileType[][] map = new com.mygdx.game.scenes.main_area.SceneMainAreaTileType[image.getWidth()][image.getHeight()];

        image.getTextureData().prepare();
        Pixmap pixelMap = image.getTextureData().consumePixmap();

        final int white = Color.rgba8888(Color.WHITE);
        final int red = Color.rgba8888(Color.RED);
        final int green = Color.rgba8888(Color.GREEN);
        final int yellow = Color.rgba8888(Color.YELLOW);
        final int blue = Color.rgba8888(Color.BLUE);
        final int black = Color.rgba8888(Color.BLACK);

        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int color = pixelMap.getPixel(i,j);

                if(color == white)
                    map[i][j] = com.mygdx.game.scenes.main_area.SceneMainAreaTileType.NORMAL;
                else if(color == red)
                    map[i][j] = com.mygdx.game.scenes.main_area.SceneMainAreaTileType.ENEMY;
                else if(color == yellow)
                    map[i][j] = com.mygdx.game.scenes.main_area.SceneMainAreaTileType.CHEST;
                else if(color == blue)
                    map[i][j] = com.mygdx.game.scenes.main_area.SceneMainAreaTileType.SPAWN;
                else if(color == green)
                    map[i][j] = com.mygdx.game.scenes.main_area.SceneMainAreaTileType.DOOR;
                else
                    map[i][j] = com.mygdx.game.scenes.main_area.SceneMainAreaTileType.NONE;
            }
        }

        return map;
    }

    public static com.mygdx.game.scenes.battle.SceneBattleTileType[][] getBattleTileTypes(Texture image) {
        com.mygdx.game.scenes.battle.SceneBattleTileType[][] map = new com.mygdx.game.scenes.battle.SceneBattleTileType[image.getWidth()][image.getHeight()];

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
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.NEUTRAL;
                else if(color == red)
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.ENEMY;
                else if(color == blue)
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.FRIENDLY;
                else if(color == black)
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.NONE;
                else if(color == green)
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.SPAWN;
                else if(color == yellowFriendly)
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.OBJECT_FRIENDLY;
                else if(color == yellowEnemy)
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.OBJECT_ENEMY;
                else
                    map[i][j] = com.mygdx.game.scenes.battle.SceneBattleTileType.NONE;
            }
        }

        return map;
    }
}
