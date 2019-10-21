package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.graphics.Image;

public abstract class PotionCard extends Card {

    public PotionCard(String name, int lockInitial, int lockFinal, String description, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, lockInitial, lockFinal, description, type, pointsCost, quality, element);

        if(quality.equals(Quality.STANDARD))
        {
            icon = Image.getImage("items/cards/potions/Potion_icon");
        } else {
            icon = Image.getImage("items/cards/potions/Potion_" + quality.getStr() + "_icon");
        }

        if(element != null) {
            if(quality.equals(Quality.STANDARD))
            {
                overlay_texture = Image.getImage("items/cards/potions/Potion_overlay");
            } else {
                overlay_texture = Image.getImage("items/cards/potions/Potion_" + quality.getStr() + "_overlay");
            }
        }
    }

}