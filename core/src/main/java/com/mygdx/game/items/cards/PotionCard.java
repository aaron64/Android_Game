package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;

public abstract class PotionCard extends Card {

    public PotionCard(String name, int lockInitial, int lockFinal, String description, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, lockInitial, lockFinal, description, type, pointsCost, quality, element);

        if(quality.equals(Quality.STANDARD))
        {
            icon = new Texture("items/cards/potions/Potion_icon.png");
        } else {
            icon = new Texture("items/cards/potions/Potion_" + quality.getStr() + "_icon.png");
        }

        if(element != null) {
            if(quality.equals(Quality.STANDARD))
            {
                overlay_texture = new Texture("items/cards/potions/Potion_overlay.png");
            } else {
                overlay_texture = new Texture("items/cards/potions/Potion_" + quality.getStr() + "_overlay.png");
            }
        }
    }

}