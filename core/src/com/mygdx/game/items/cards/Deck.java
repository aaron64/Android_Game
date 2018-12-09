package com.mygdx.game.items.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private int maxCards;
    private ArrayList<Card> cards;
    public Deck(int maxCards) {
        this.maxCards = maxCards;
        cards = new ArrayList<Card>();
    }

    public Card getCard(int index) {
        return cards.remove(index);
    }

    public Card peekCard(int index) {
        return cards.get(index);
    }

    public Card peekCard() { return cards.get(cards.size()-1); }

    public void addCard(Card c) {
        cards.add(c);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }

    public Card pop() {
        if(cards.size() > 0)
            return cards.remove(cards.size()-1);
        return null;
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
