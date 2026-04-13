package bsp.blackjack;

import bsp.blackjack.Model.*;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    private final Mypane view;
    private Deck kartenstapel;
    private final Player spieler = new Player();
    private final Player computer = new Player();
    private final StatsManager statistik = new StatsManager();

    public HelloController(Mypane view) {
        this.view = view;
        view.aktualisiereStatistik(statistik.getAnzahlSpiele(), statistik.getAnzahlSiege());
    }

    public void neuesSpiel() {
        kartenstapel = new Deck();
        spieler.resetHand();
        computer.resetHand();
        view.entferneAlleKarten();
        view.aktiviereKnöpfe();

        karteZiehen(spieler, true);
        karteZiehen(spieler, true);
        zeigePunkte();
    }

    public void spielerZieht() {
        karteZiehen(spieler, true);
        zeigePunkte();
        if (spieler.getScore() > 21) {
            view.setzePunktestandText("Überkauft! (" + spieler.getScore() + ")");
            statistik.spielSpeichern(false);
            view.aktualisiereStatistik(statistik.getAnzahlSpiele(), statistik.getAnzahlSiege());
            view.deaktiviereKnöpfe();
        }
    }

    public void spielerBleibt() {
        view.deaktiviereKnöpfe();
        computerZieht();
    }

    private void computerZieht() {
        if (computer.getScore() < 17) {
            PauseTransition pause = new PauseTransition(Duration.millis(800));
            pause.setOnFinished(e -> {
                karteZiehen(computer, false);
                computerZieht();
            });
            pause.play();
        } else {
            auswerten();
        }
    }

    private void karteZiehen(Player person, boolean istSpieler) {
        Card karte = kartenstapel.karteZiehen();
        person.addCard(karte);

        ImageView bild = new ImageView(new Image(getClass().getResourceAsStream(karte.getBildPfad())));
        bild.setFitWidth(100);
        bild.setPreserveRatio(true);
        bild.setLayoutX(50 + person.getHand().size() * 110);
        bild.setLayoutY(istSpieler ? 100 : 250);

        view.zeigeKarte(bild);
    }

    private void zeigePunkte() {
        view.setzePunktestandText("Punkte: " + spieler.getScore());
    }

    private void auswerten() {
        int punkteSpieler = spieler.getScore();
        int punkteComputer = computer.getScore();
        String ergebnis;
        boolean gewonnen = false;

        if (punkteComputer > 21 || punkteSpieler > punkteComputer) {
            ergebnis = "Du gewinnst!";
            gewonnen = true;
        } else if (punkteSpieler < punkteComputer) {
            ergebnis = "Computer gewinnt!";
        } else {
            ergebnis = "Unentschieden!";
        }

        statistik.spielSpeichern(gewonnen);
        view.setzePunktestandText("Spieler: " + punkteSpieler + " | Computer: " + punkteComputer + " → " + ergebnis);
        view.aktualisiereStatistik(statistik.getAnzahlSpiele(), statistik.getAnzahlSiege());
    }
}
