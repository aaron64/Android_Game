package com.mygdx.game.entities.Deck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class DeckCardSlot extends Entity {

    private static Texture cardBackground = new Texture("misc/icon_holder_battle.png");

    private BitmapFont nameFont, descriptionFont;
    private Card card;
    private Vector2i iconSize;

    private Vector2f namePos, descriptionPos;
    public DeckCardSlot(Card card, Vector2f pos, Vector2i size) {
        super(pos, "CARDSLOT");
        this.setSize(size);

        nameFont = FontUtil.getFont(48);
        descriptionFont = FontUtil.getFont(20);


        this.card = card;

        iconSize = new Vector2i(size.y, size.y);

        namePos = new Vector2f(getPos());
        namePos.x += iconSize.x + 6;
        namePos.y += iconSize.x-20;

        descriptionPos = new Vector2f(getPos());
        descriptionPos.x += iconSize.x + namePos.x;
        namePos.y += iconSize.x-20;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(cardBackground, getPos(), getSize());
        rs.draw(card.getIcon(), getPos(), iconSize);
        rs.drawText(nameFont, card.getName(), namePos);
        rs.drawText(descriptionFont, card.getDescription(), descriptionPos);
    }
}
