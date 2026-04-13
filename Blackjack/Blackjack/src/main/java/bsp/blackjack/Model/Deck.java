package bsp.blackjack.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> stapel = new ArrayList<>();

    public Deck() {
        String[] farben = {"hearts", "diamonds", "clubs", "spades"};
        String[] werte = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
        for (String farbe : farben) {
            for (String wert : werte) {
                int punktwert = switch (wert) {
                    case "jack", "queen", "king" -> 10;
                    case "ace" -> 11;
                    default -> Integer.parseInt(wert);
                };

                String name = wert + "_of_" + farbe;
                stapel.add(new Card(name, punktwert));
            }
        }

        Collections.shuffle(stapel); // Karten mischen
    }

    public Card karteZiehen() {
        if (stapel.isEmpty()) {
            throw new IllegalStateException("Keine Karten mehr im Stapel!");
        }
        return stapel.remove(0);
    }
}
