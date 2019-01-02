package com.mygdx.game.items.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private int maxCards;
    private ArrayList<Card> cards;
    private ArrayList<Card> removeList;

    public Deck(int maxCards) {
        this.maxCards = maxCards;
        cards = new ArrayList<Card>();
        removeList = new ArrayList<Card>();
    }

    public void refresh() {
        cards.removeAll(removeList);
        removeList.clear();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public Card peekCard(int index) {
        return cards.get(index);
    }

    public Card peekCard() { return cards.get(cards.size()-1); }

    public void addCard(Card c) {
        cards.add(c);
    }

    public void addCard(Card c, int i) {
        cards.add(i, c);
    }

    public void remove(int i) {
        removeList.add(cards.get(i));
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
        if(cards.size() > 0) {
            Card card = cards.get(cards.size() - 1);
            removeList.add(card);
            return card;
        }
        return null;
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
