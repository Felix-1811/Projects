package bsp.blackjack.Model;

import java.io.*;
import java.nio.file.*;

public class StatsManager {

        private static final String dateipfard = "statistik.txt";
        private int anzahlSpiele = 0;
        private int anzahlSiege = 0;

        public StatsManager() {
            lade();
        }

        private void lade() {
            try {
                if (Files.exists(Path.of(dateipfard))) {
                    BufferedReader reader = new BufferedReader(new FileReader(dateipfard));
                    anzahlSpiele = Integer.parseInt(reader.readLine());
                    anzahlSiege = Integer.parseInt(reader.readLine());
                    reader.close();
                }
            } catch (IOException | NumberFormatException e) {
                anzahlSpiele = 0;
                anzahlSiege = 0;
            }
        }

        private void speichere() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateipfard))) {
                writer.write(anzahlSpiele + "\n");
                writer.write(anzahlSiege + "\n");
            } catch (IOException e) {
                System.err.println("Fehler beim Speichern der Statistik: " + e.getMessage());
            }
        }

        public void spielSpeichern(boolean spielerGewonnen) {
            anzahlSpiele++;
            if (spielerGewonnen) anzahlSiege++;
            speichere();
        }

        public int getAnzahlSpiele() {
            return anzahlSpiele;
        }

        public int getAnzahlSiege() {
            return anzahlSiege;
        }
    }
