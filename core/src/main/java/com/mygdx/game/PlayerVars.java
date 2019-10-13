package com.mygdx.game;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;


public class PlayerVars {
    public static Deck deck = new Deck(5);
    public static Deck pack = new Deck(30);
    public static Card secondaryAttack = CardFactory.buildCard("Bow", null, Quality.WEAK);
    public static int maxPoints = 7;

    public static int clickDistance = 200;

    public static void init() {

        deck.addCard(CardFactory.buildCard("Dagger", Element.GRASS));
        deck.addCard(CardFactory.buildCard("Sword", Element.FIRE));
        deck.addCard(CardFactory.buildCard("Longsword", Element.WATER));
        deck.addCard(CardFactory.buildCard("Claymore", Element.SHOCK));
        deck.addCard(CardFactory.buildCard("Cutlass", Element.POISON));
        deck.addCard(CardFactory.buildCard("Heal"));
        deck.addCard(CardFactory.buildCard("Bow", Element.WIND));
        deck.addCard(CardFactory.buildCard("Longbow", Element.GRASS));
        deck.addCard(CardFactory.buildCard("Crossbow", Element.FIRE));
        deck.addCard(CardFactory.buildCard("Magic", Element.WATER));
        deck.addCard(CardFactory.buildCard("Magic", Element.FIRE));
        deck.addCard(CardFactory.buildCard("Magic", Element.GRASS));
        deck.addCard(CardFactory.buildCard("Magic", Element.WIND));
        deck.addCard(CardFactory.buildCard("Magic", Element.SHOCK));
        deck.addCard(CardFactory.buildCard("Magic", Element.WATER));
        deck.addCard(CardFactory.buildCard("Small Bomb", Element.SHOCK));
        deck.addCard(CardFactory.buildCard("Bomb", Element.POISON));
        deck.addCard(CardFactory.buildCard("Large Bomb", Element.WIND));
        deck.addCard(CardFactory.buildCard("Horizontal Bomb", Element.GRASS));
        deck.addCard(CardFactory.buildCard("Vertical Bomb", Element.FIRE));

        pack.addCard(CardFactory.buildCard("Heal"));
        pack.addCard(CardFactory.buildCard("Heal", Quality.STRONG));
        pack.addCard(CardFactory.buildCard("Bow"));
        pack.addCard(CardFactory.buildCard("Sword"));
        pack.addCard(CardFactory.buildCard("Bow"));
        pack.addCard(CardFactory.buildCard("Bow"));
    }
}
