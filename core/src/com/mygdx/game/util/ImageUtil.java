package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;

public class ImageUtil {

    public static SceneBattleTileType[][] getBattleTileTypes(Texture image) {
        SceneBattleTileType[][] map = new SceneBattleTileType[image.getWidth()][image.getHeight()];

        image.getTextureData().prepare();
        Pixmap pixelMap = image.getTextureData().consumePixmap();

        final int white = Color.argb8888(Color.WHITE);
        final int red = -Color.argb8888(Color.RED) - 1;
        final int blue = Color.argb8888(Color.BLUE);
        final int black = Color.argb8888(Color.BLACK);

        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int color = pixelMap.getPixel(i,j);

                if(color == white)
                    map[i][j] = SceneBattleTileType.NEUTRAL;
                else if(color == red)
                    map[i][j] = SceneBattleTileType.FRIENDLY;
                else if(color == blue)
                    map[i][j] = SceneBattleTileType.ENEMY;
                else if(color == black)
                    map[i][j] = SceneBattleTileType.NONE;
                else
                    map[i][j] = SceneBattleTileType.NONE;
            }
        }

        return map;
    }
}
