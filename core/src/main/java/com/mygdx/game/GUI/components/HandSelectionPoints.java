package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.graphics.ContentBackground;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class HandSelectionPoints extends GUIComponent {

    private SceneHandSelect scene;
    private int maxPoints, usedPoints;

    private Vector2f pos;
    private Vector2i size;

    private BitmapFont font;
    private Vector2f fontPos;

    public HandSelectionPoints(GUI gui, Vector2f pos, Vector2i size, SceneHandSelect scene) {
        super(gui, "HAND_SELECTION_POINTS");

        this.pos = pos;
        this.size = size;

        this.scene = scene;

        maxPoints = PlayerVars.maxPoints;
        usedPoints = 0;

        font = FontUtil.getFont(32);
        fontPos = new Vector2f(pos.x + size.w()/2, pos.y + size.h()/2);
    }

    @Override
    public void update(Scene scene) {
        usedPoints = this.scene.getPointsUsed();
    }

    @Override
    public void render(RenderSystem rs) {
        ContentBackground.drawBackground(rs, pos, size);
        rs.drawTextCentered(font, "" + (maxPoints - usedPoints), fontPos);
    }

    public boolean spent(Card card) {
        return usedPoints + card.getPointsCost() > maxPoints;
    }
}
