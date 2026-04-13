package bsp.blackjack.Model;

public class Card {

    private final String name;   // z. B. "2_of_hearts"
    private final int wert;      // Punktewert der Karte

    public Card(String name, int wert) {
        this.name = name;
        this.wert = wert;
    }

    public String getName() {
        return name;
    }

    public int getWert() {
        return wert;
    }

    public String getBildPfad() {
        return "/bilder/" + name + ".png";  // Bilddateipfad für ImageView
    }

    @Override
    public String toString() {
        return name + " (" + wert + ")";
    }
}
