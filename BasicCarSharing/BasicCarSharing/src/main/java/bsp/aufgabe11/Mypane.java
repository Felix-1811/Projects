package bsp.aufgabe11;

import bsp.aufgabe11.MODEL.*;
import bsp.aufgabe11.MODEL.Controller;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Mypane extends BorderPane {

    // Attribute
    private Fahrzeugvermietung fahrzeugvermietung;
    private ListView<Fahrzeug> fahrzeugListView;
    private bsp.aufgabe11.MODEL.Controller controller;

    // Konstruktor
    public Mypane() throws UngueltigerParameterException {
        // === Model erstellen ===
        fahrzeugvermietung = new Fahrzeugvermietung();

        // === Controller erstellen ===
        controller = new Controller(fahrzeugvermietung);

        // === View erstellen ===
        fahrzeugListView = new ListView<>();
        fahrzeugListView.setItems(fahrzeugvermietung.getFahrzeuge());
        this.setCenter(fahrzeugListView);

        // === Menü erstellen ===
        MenuBar menuBar = new MenuBar();
        Menu dateiMenu = new Menu("Datei");

        // Menüeinträge
        MenuItem hinzufuegenItem = new MenuItem("Hinzufügen");
        MenuItem entfernenItem = new MenuItem("Entfernen");
        MenuItem vermietenItem = new MenuItem("Vermieten");
        MenuItem zurueckgebenItem = new MenuItem("Zurückgeben");
        MenuItem beendenItem = new MenuItem("Beenden");

        // Menüeinträge zum Menü hinzufügen
        dateiMenu.getItems().addAll(hinzufuegenItem, entfernenItem, vermietenItem, zurueckgebenItem, beendenItem);

        // Menü zur MenüBar hinzufügen
        menuBar.getMenus().add(dateiMenu);

        // MenüBar ins Top setzen
        this.setTop(menuBar);

        // === Testfahrzeuge einfügen (nur zum Start) ===
        fahrzeugvermietung.hinzufuegen(new Fahrzeug(Fahrzeugkategorie.PKW, 50000, false));
        fahrzeugvermietung.hinzufuegen(new Fahrzeug(Fahrzeugkategorie.SUV, 30000, false));
        fahrzeugvermietung.hinzufuegen(new Fahrzeug(Fahrzeugkategorie.LKW, 150000, false));

        // === Listener / Aktionen verbinden ===
        hinzufuegenItem.setOnAction(e -> fahrzeugHinzufuegen());
        entfernenItem.setOnAction(e -> fahrzeugEntfernen());
        beendenItem.setOnAction(e -> programmBeenden());
        vermietenItem.setOnAction(e -> {
            try {
                fahrzeugVermieten();
            } catch (UngueltigerParameterException ex) {
                throw new RuntimeException(ex);
            }
        });
        zurueckgebenItem.setOnAction(e -> fahrzeugZurueckgeben());


    }

    private void programmBeenden() {
        System.exit(0);
    }

    private void fahrzeugZurueckgeben() {
        try {
            Fahrzeug ausgewaehlt = fahrzeugListView.getSelectionModel().getSelectedItem();

            if (ausgewaehlt != null) {
                // 1. Kilometerstand abfragen
                javafx.scene.control.TextInputDialog dialog = new javafx.scene.control.TextInputDialog();
                dialog.setTitle("Fahrzeug zurückgeben");
                dialog.setHeaderText("Kilometerstand eingeben");
                dialog.setContentText("Neuer Kilometerstand:");

                java.util.Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {
                    float neuerKmStand = Float.parseFloat(result.get());

                    // 2. Controller aufrufen zum Zurückgeben
                    float kosten = controller.fahrzeugZurueckgeben(ausgewaehlt, neuerKmStand);

                    // 3. Kosten ausgeben
                    System.out.println("Mietkosten: " + kosten + " €");
                }
            } else {
                System.out.println("Kein Fahrzeug ausgewählt!");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Ungültige Kilometerangabe!");
        } catch (UngueltigerParameterException ex) {
            ex.printStackTrace();
        }
    }


    private void fahrzeugVermieten() throws UngueltigerParameterException {
        Fahrzeug ausgewaehlt = fahrzeugListView.getSelectionModel().getSelectedItem();
        if(ausgewaehlt != null) {
            controller.fahrzeugVermieten(ausgewaehlt);
        }else{
            System.out.println("Kein Fahrzeug ausgewählt");
        }

    }

    private void fahrzeugEntfernen() {
        try{
            Fahrzeug ausgewaehlt = fahrzeugListView.getSelectionModel().getSelectedItem();
            if(ausgewaehlt != null){
                controller.fahrzeugEntfernen(ausgewaehlt);
            }else{
                throw new UngueltigerParameterException("Kein Fahrzeug ausgewählt");
            }

        }catch(UngueltigerParameterException ex){
            ex.printStackTrace();
        }
    }

    private void fahrzeugHinzufuegen() {
        try {
            // === 1. Eingabe: Kilometerstand abfragen ===
            javafx.scene.control.TextInputDialog dialog = new javafx.scene.control.TextInputDialog();
            dialog.setTitle("Neues Fahrzeug hinzufügen");
            dialog.setHeaderText("Fahrzeug erstellen");
            dialog.setContentText("Bitte Kilometerstand eingeben:");

            java.util.Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                float kmStand = Float.parseFloat(result.get());

                // === 2. Eingabe: Fahrzeugkategorie auswählen ===
                java.util.List<Fahrzeugkategorie> kategorien = java.util.Arrays.asList(Fahrzeugkategorie.values());
                javafx.scene.control.ChoiceDialog<Fahrzeugkategorie> choiceDialog = new javafx.scene.control.ChoiceDialog<>(Fahrzeugkategorie.PKW, kategorien);
                choiceDialog.setTitle("Fahrzeugkategorie auswählen");
                choiceDialog.setHeaderText("Kategorie wählen");
                choiceDialog.setContentText("Bitte Fahrzeugkategorie auswählen:");

                java.util.Optional<Fahrzeugkategorie> ausgewaehlt = choiceDialog.showAndWait();

                if (ausgewaehlt.isPresent()) {
                    Fahrzeugkategorie kategorie = ausgewaehlt.get();

                    // === 3. Fahrzeug über Controller hinzufügen ===
                    controller.fahrzeugHinzufuegen(kategorie, kmStand);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Ungültige Zahl eingegeben!");
        } catch (UngueltigerParameterException ex) {
            ex.printStackTrace();
        }
    }

}
