package com.mygdx.game;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.CardBuilder;
import com.mygdx.game.items.cards.Deck;

public class PlayerVars {
    public static Deck deck = new Deck(5);
    public static Deck pack = new Deck(30);
    public static Card secondaryAttack = CardBuilder.buildCard("Bow", null, Quality.WEAK);
    public static int maxPoints = 7;

    public static void init() {

        deck.addCard(CardBuilder.buildCard("Bow", Element.GRASS));
        deck.addCard(CardBuilder.buildCard("Bow"));
        deck.addCard(CardBuilder.buildCard("Magic", Element.POISON));
        deck.addCard(CardBuilder.buildCard("Magic", Element.GRASS));
        deck.addCard(CardBuilder.buildCard("Sword"));

        pack.addCard(CardBuilder.buildCard("Bow"));
        pack.addCard(CardBuilder.buildCard("Sword"));
        pack.addCard(CardBuilder.buildCard("Bow"));
        pack.addCard(CardBuilder.buildCard("Bow"));
    }
}
