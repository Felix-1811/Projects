package bsp.blackjack;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Mypane extends Pane {

    private final Label punktestandAnzeige = new Label("Punkte: 0");
    private final Button ziehenKnopf = new Button("Karte ziehen");
    private final Button stoppenKnopf = new Button("Stop");
    private final Button neuesSpielKnopf = new Button("Neues Spiel");
    private final Label statistikAnzeige = new Label();

    private final HelloController steuerung;

    public Mypane() {
        setPrefSize(600, 500);

        neuesSpielKnopf.setLayoutX(300);
        neuesSpielKnopf.setLayoutY(400);
        statistikAnzeige.setLayoutX(400);
        statistikAnzeige.setLayoutY(10);
        punktestandAnzeige.setLayoutX(50);
        punktestandAnzeige.setLayoutY(30);
        ziehenKnopf.setLayoutX(50);
        ziehenKnopf.setLayoutY(400);
        stoppenKnopf.setLayoutX(150);
        stoppenKnopf.setLayoutY(400);

        getChildren().addAll(
                neuesSpielKnopf,
                statistikAnzeige,
                punktestandAnzeige,
                ziehenKnopf,
                stoppenKnopf
        );

        steuerung = new HelloController(this);

        ziehenKnopf.setOnAction(e -> steuerung.spielerZieht());
        stoppenKnopf.setOnAction(e -> steuerung.spielerBleibt());
        neuesSpielKnopf.setOnAction(e -> steuerung.neuesSpiel());

        steuerung.neuesSpiel();
    }


    public void setzePunktestandText(String text) {
        punktestandAnzeige.setText(text);
    }

    public void zeigeKarte(ImageView bild) {
        getChildren().add(bild);
    }

    public void deaktiviereKnöpfe() {
        ziehenKnopf.setDisable(true);
        stoppenKnopf.setDisable(true);
    }

    public void aktiviereKnöpfe() {
        ziehenKnopf.setDisable(false);
        stoppenKnopf.setDisable(false);
    }

    public void entferneAlleKarten() {
        getChildren().removeIf(node -> node instanceof ImageView);
    }

    public void aktualisiereStatistik(int gesamtSpiele, int siege) {
        statistikAnzeige.setText("Spiele: " + gesamtSpiele + " | Siege: " + siege);
    }
}
