package com.mygdx.game;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.items.cards.Deck;


public class PlayerVars {
    public static Deck deck = new Deck(5);
    public static Deck pack = new Deck(30);
    public static com.mygdx.game.items.cards.Card secondaryAttack = CardFactory.buildCard("Bow", null, Quality.WEAK);
    public static int maxPoints = 7;

    public static void init() {

        deck.addCard(CardFactory.buildCard("Bow", Element.GRASS));
        deck.addCard(CardFactory.buildCard("Bow"));
        deck.addCard(CardFactory.buildCard("Magic", Element.POISON));
        deck.addCard(CardFactory.buildCard("Magic", Element.GRASS));
        deck.addCard(CardFactory.buildCard("Sword"));
        deck.addCard(CardFactory.buildCard("Bomb"));
        deck.addCard(CardFactory.buildCard("Heal"));

        pack.addCard(CardFactory.buildCard("Bow"));
        pack.addCard(CardFactory.buildCard("Sword"));
        pack.addCard(CardFactory.buildCard("Bow"));
        pack.addCard(CardFactory.buildCard("Bow"));
    }
}
