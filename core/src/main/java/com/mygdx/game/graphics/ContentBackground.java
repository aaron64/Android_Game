package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class ContentBackground {

    private static Texture boxTL = new Texture("gui/box/boxTL.png");
    private static Texture boxT = new Texture("gui/box/boxT.png");
    private static Texture boxTR = new Texture("gui/box/boxTR.png");
    private static Texture boxL = new Texture("gui/box/boxL.png");
    private static Texture boxC = new Texture("gui/box/boxC.png");
    private static Texture boxR = new Texture("gui/box/boxR.png");
    private static Texture boxBL = new Texture("gui/box/boxBL.png");
    private static Texture boxB = new Texture("gui/box/boxB.png");
    private static Texture boxBR = new Texture("gui/box/boxBR.png");

    private static int scale = 4;

    public static void drawBackground(RenderSystem rs, Vector2f pos, Vector2i size) {
        int cornerSize = boxTL.getWidth() * scale;
        int horizontalSize = size.w() - cornerSize * 2;
        int verticalSize = size.h() - cornerSize * 2;

        rs.draw(boxBL, pos.x, pos.y, cornerSize, cornerSize);
        rs.draw(boxB, pos.x + cornerSize, pos.y, horizontalSize, cornerSize);
        rs.draw(boxBR, pos.x + cornerSize + horizontalSize, pos.y, cornerSize, cornerSize);

        rs.draw(boxL, pos.x, pos.y + cornerSize, cornerSize, verticalSize);
        rs.draw(boxC, pos.x + cornerSize, pos.y + cornerSize, horizontalSize, verticalSize);
        rs.draw(boxR, pos.x + cornerSize + horizontalSize, pos.y + cornerSize, cornerSize, verticalSize);

        rs.draw(boxTL, pos.x, pos.y + cornerSize + verticalSize, cornerSize, cornerSize);
        rs.draw(boxT, pos.x + cornerSize, pos.y + cornerSize + verticalSize, horizontalSize, cornerSize);
        rs.draw(boxTR, pos.x + cornerSize + horizontalSize, pos.y + cornerSize + verticalSize, cornerSize, cornerSize);
    }
}
