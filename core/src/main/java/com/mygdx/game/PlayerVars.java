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

        deck.addCard(CardFactory.buildCard("Dagger", Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Sword",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Longsword",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Claymore",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Cutlass",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Heal", Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Bow",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Longbow",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Crossbow",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Magic",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Magic",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Magic",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Magic",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Magic",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Magic",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Small Bomb",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Bomb",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Horizontal Bomb",  Element.getRandomElement(true), Quality.getRandomQuality()));
        deck.addCard(CardFactory.buildCard("Vertical Bomb",  Element.getRandomElement(true), Quality.getRandomQuality()));

        pack.addCard(CardFactory.buildCard("Heal"));
        pack.addCard(CardFactory.buildCard("Heal", Quality.STRONG));
        pack.addCard(CardFactory.buildCard("Bow"));
        pack.addCard(CardFactory.buildCard("Sword"));
        pack.addCard(CardFactory.buildCard("Bow"));
        pack.addCard(CardFactory.buildCard("Bow"));
    }
}
