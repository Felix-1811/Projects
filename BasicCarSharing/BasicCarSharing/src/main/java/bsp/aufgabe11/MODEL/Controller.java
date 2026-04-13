package bsp.aufgabe11.MODEL;

import java.io.UnsupportedEncodingException;

public class Controller {
    private Fahrzeugvermietung fahrzeugvermietung;
    public Controller (Fahrzeugvermietung fahrzeugvermietung) {
        this.fahrzeugvermietung = fahrzeugvermietung;
    }
    public void fahrzeugHinzufuegen(Fahrzeugkategorie kategorie, float kmStand) throws  UngueltigerParameterException {
        fahrzeugvermietung.hinzufuegen(new Fahrzeug(kategorie, kmStand, false));

    }

    public void fahrzeugEntfernen(Fahrzeug ausgewaehlt) throws UngueltigerParameterException {
        fahrzeugvermietung.entfernen(ausgewaehlt);
    }

    public void fahrzeugVermieten(Fahrzeug ausgewaehlt) throws UngueltigerParameterException {
        fahrzeugvermietung.vermieten(ausgewaehlt);
    }

    public float fahrzeugZurueckgeben(Fahrzeug fahrzeug, float neuerKmStand) throws UngueltigerParameterException {
        return fahrzeugvermietung.zurueckgeben(fahrzeug, neuerKmStand);
    }
}
