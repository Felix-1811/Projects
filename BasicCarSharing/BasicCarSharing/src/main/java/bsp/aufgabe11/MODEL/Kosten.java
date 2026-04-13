package bsp.aufgabe11.MODEL;

public class Kosten {



    private float pauschale;
    private float preisProKm;
        public Kosten(float pauschale, float preisProKm) throws UngueltigerParameterException {
            setPauschale(pauschale);
            setPreisProKm(preisProKm);
        }
        public float getPauschale() {
            return pauschale;
        }

        public void setPauschale(float pauschale) throws UngueltigerParameterException {
            if (pauschale < 0) {
                throw new UngueltigerParameterException("Pauschale darf nicht negativ sein!");
            }
            this.pauschale = pauschale;
        }

        public float getPreisProKm() {
            return preisProKm;
        }

        public void setPreisProKm(float preisProKm) throws UngueltigerParameterException {
            if (preisProKm < 0) {
                throw new UngueltigerParameterException("Preis pro km darf nicht negativ sein!");
            }
            this.preisProKm = preisProKm;
        }

        @Override
        public String toString() {
            return "Pauschale: " + pauschale + " €, Preis pro km: " + preisProKm + " €";
        }
}
