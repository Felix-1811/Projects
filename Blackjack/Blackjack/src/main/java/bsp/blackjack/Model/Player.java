package bsp.blackjack.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> hand = new ArrayList<>();

    public void addCard(Card karte) {
        hand.add(karte);
    }

    public int getScore() {
        int punkte = 0;
        for (Card k : hand) {
            punkte += k.getWert();
        }
        return punkte;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand); // damit man draußen nichts verändern kann
    }

    public void resetHand() {
        hand.clear();
    }

    @Override
    public String toString() {
        return "Hand: " + hand + " | Punkte: " + getScore();
    }
}
