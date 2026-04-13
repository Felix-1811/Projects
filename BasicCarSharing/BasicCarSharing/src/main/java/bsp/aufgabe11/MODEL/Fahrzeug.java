package bsp.aufgabe11.MODEL;

public class Fahrzeug {
    private float kmStandBegin;
    private float kmStand;
    private boolean vermietet;
    private Fahrzeugkategorie fahrzeugkategorie;
    public Fahrzeug(Fahrzeugkategorie fahrzeugkategorie, float kmStand, boolean vermietet) throws UngueltigerParameterException {
        setKmStand(kmStand);
        setVermietet(vermietet);
        setFahrzeugkategorie(fahrzeugkategorie);
    }

    //Get Und set Methoden

    public float getKmStand() {return kmStand;}

    public void setKmStand(float kmStand) throws UngueltigerParameterException {
       if(kmStand < 0){
           throw new UngueltigerParameterException("KM Stand darf nicht unter null sein!");
       }
        this.kmStand = kmStand;
    }

    public boolean isVermietet() {return vermietet;}

    public void setVermietet(boolean vermietet) {this.vermietet = vermietet;}
    public void setFahrzeugkategorie(Fahrzeugkategorie fahrzeugkategorie) throws UngueltigerParameterException {
        if(fahrzeugkategorie == null){
            throw new UngueltigerParameterException("Fahrzeugkategorie wurde null!");
        }
        this.fahrzeugkategorie = fahrzeugkategorie;
    }
    public Fahrzeugkategorie getFahrzeugkategorie(){return fahrzeugkategorie;}
    //checkt ob schon vermietet -> wenn nein dann exception (wird für später gebraucht
    public void vermieten() throws UngueltigerParameterException {
        if (vermietet) {
            throw new UngueltigerParameterException("Fahrzeug ist bereits vermietet!");
        }
        this.vermietet = true;
        this.kmStandBegin = this.kmStand;
    }
    //Zurueckgeben wird beim zuruckgeben verwendet checkt ein paar dinge
    public float zurueckgeben(float neuerKmStand) throws UngueltigerParameterException {
        if (!vermietet) {
            throw new UngueltigerParameterException("Fahrzeug ist nicht vermietet!");
        }
        if(neuerKmStand < this.kmStandBegin){
            throw new UngueltigerParameterException("Neuer Kilometerstand ist kleiner als der davor");
        }
        this.vermietet = false;
        this.kmStand = neuerKmStand;
        float gefahreneKm = neuerKmStand - this.kmStandBegin;
        return gefahreneKm;
    }
    @Override
    public String toString() {
        String status = vermietet ? "VERMIETET" : "VERFÜGBAR";
        return fahrzeugkategorie + " - " + kmStand + " km - " + status;
    }



}
