package com.mygdx.game.GUI.components;

import com.mygdx.game.graphics.ContentBackground;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class BattleDeckComponent extends GUIComponent {

    private Vector2i size;

    private int posLeft;
    private int posTop;

    private BattlePlayer player;

    public BattleDeckComponent(GUI gui, BattlePlayer player) {
        super(gui, "DECK");
        this.player = player;

        posLeft = Window.percLeft(0.15f);
        posTop = Window.percTop(0.30f);

        size = new Vector2i(Window.percHeight(0.20f), Window.percHeight(0.20f));
    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public void render(RenderSystem rs) {
        for(int i = 0; i < player.getHand().getSize(); i++) {
            Vector2f pos = new Vector2f(posLeft + i * 20, posTop);

            ContentBackground.drawBackground(rs, pos, size);
            Card card = player.getHand().peekCard(i);
            card.drawIcon(rs, pos, size);
        }
    }
}
