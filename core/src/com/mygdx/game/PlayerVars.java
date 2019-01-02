package com.mygdx.game;

import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.items.cards.Deck;

public class PlayerVars {
    public static Deck deck = new Deck(5);
    public static Deck pack = new Deck(30);

    public static void init() {

        deck.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Magic"));
        deck.addCard(CardLoader.buildCard("Sword"));

        pack.addCard(CardLoader.buildCard("Bow"));
        pack.addCard(CardLoader.buildCard("Sword"));
        pack.addCard(CardLoader.buildCard("Bow"));
        pack.addCard(CardLoader.buildCard("Bow"));
    }
}
