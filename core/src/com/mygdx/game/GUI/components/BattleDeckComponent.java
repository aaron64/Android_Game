package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

public class BattleDeckComponent extends GUIComponent {

    private Deck deck;
    private Texture iconBackground;

    private Vector2i size;

    private int posLeft;
    private int posTop;
    public BattleDeckComponent(Deck deck) {
        super("DECK");
        this.deck = deck;
        iconBackground = new Texture("misc/icon_holder_battle.png");

        posLeft = Window.percLeft(0.15f);
        posTop = Window.percTop(0.30f);

        size = new Vector2i(Window.percHeight(0.20f), Window.percHeight(0.20f));
    }

    @Override
    public void update(GUI gui, Scene scene) {

    }

    @Override
    public void render(GUI gui, RenderSystem rs) {
        for(int i = 0; i < deck.getSize(); i++) {
            Vector2f pos = new Vector2f(posLeft + i * 20, posTop);
            rs.draw(iconBackground, pos, size);
            deck.peekCard(i).drawIcon(rs, pos, size);
        }
    }
}
