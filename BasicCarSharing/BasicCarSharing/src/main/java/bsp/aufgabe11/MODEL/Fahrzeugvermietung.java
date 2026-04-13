package bsp.aufgabe11.MODEL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class Fahrzeugvermietung {
    private ObservableList<Fahrzeug> fahrzeuge;
    private Map<Fahrzeugkategorie, Kosten> kostentabelle;

    public Fahrzeugvermietung() throws UngueltigerParameterException {
        fahrzeuge = FXCollections.observableArrayList();
        kostentabelle = new HashMap<>();
        kostentabelle.put(Fahrzeugkategorie.PKW, new Kosten(50.0f, 0.30f));
        kostentabelle.put(Fahrzeugkategorie.SUV, new Kosten(70.0f, 0.40f));
    }

    public ObservableList<Fahrzeug> getFahrzeuge() {
        return fahrzeuge;
    }

    public Map<Fahrzeugkategorie, Kosten> getKostentabelle() {
        return kostentabelle;
    }
    public void hinzufuegen(Fahrzeug fahrzeug) throws UngueltigerParameterException {
        if(fahrzeug == null) { //wenn null -> Error
            throw new UngueltigerParameterException("Fahrzeug null");
        }
        fahrzeuge.add(fahrzeug); //hinzufuegen
    }
    public void entfernen(Fahrzeug fahrzeug) throws UngueltigerParameterException {
        if(fahrzeug == null) {
            throw new UngueltigerParameterException("Fahrzeug null");
        }
        if(!fahrzeuge.contains(fahrzeug)) {
            throw new UngueltigerParameterException("Fahrzeug existiert nicht");
        }
        fahrzeuge.remove(fahrzeug);
    }
    public void vermieten(Fahrzeug fahrzeug) throws UngueltigerParameterException {
        if (fahrzeug == null) {
            throw new UngueltigerParameterException("Kein Fahrzeug angegeben!");
        }
        if (fahrzeug.isVermietet()) {
            throw new UngueltigerParameterException("Fahrzeug ist bereits vermietet!");
        }
        fahrzeug.vermieten();
    }

    public float zurueckgeben(Fahrzeug fahrzeug, float neuerKmStand) throws UngueltigerParameterException {
        if(fahrzeug == null) {
            throw new UngueltigerParameterException("Fahrzeug null");
        }
        if(!fahrzeug.isVermietet()){
            throw new UngueltigerParameterException("Fahrzeug wurde überhaupt nicht vermietet!");
        }
        float gefahreneKm = fahrzeug.zurueckgeben(neuerKmStand);
        Kosten kosten = kostentabelle.get(fahrzeug.getFahrzeugkategorie());
        float insgesamteKosten = kosten.getPauschale();
        if(gefahreneKm >100){
            insgesamteKosten = insgesamteKosten + kosten.getPreisProKm()*(gefahreneKm-100);
        }
        return insgesamteKosten;

    }
}
