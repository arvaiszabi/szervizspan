package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Gepjarmu {
    private SimpleStringProperty Nev;
    private SimpleStringProperty Kontakt;
    private SimpleStringProperty Gyarto;
    private SimpleStringProperty Tipus;
    private SimpleStringProperty Rendszam;
    private SimpleStringProperty Hiba;
    private SimpleBooleanProperty Kesz;

    public Gepjarmu(String nev, String kontakt, String gyarto, String tipus, String rendszam, String hiba, String kesz) {
        Nev = new SimpleStringProperty(nev);
        Kontakt = new SimpleStringProperty(kontakt);
        Gyarto = new SimpleStringProperty(gyarto);
        Tipus = new SimpleStringProperty(tipus);
        Rendszam = new SimpleStringProperty(rendszam);
        Hiba = new SimpleStringProperty(hiba);
        if(kesz.equals("true"))
            this.Kesz = new SimpleBooleanProperty(true);
        else
            this.Kesz = new SimpleBooleanProperty(false);
    }

    public String getNev() {
        return Nev.get();
    }

    public String getTelefon() {
        return Kontakt.get();
    }

    public String getGyarto() {
        return Gyarto.get();
    }

    public String getTipus() {
        return Tipus.get();
    }

    public String getRendszam() {
        return Rendszam.get();
    }

    public String getHiba() {
        return Hiba.get();
    }

    public String getKesz() {
        if(Kesz.get())
            return "Elkészült!";
        else
            return "Folyamatban...";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !o.getClass().getSimpleName().equals("Gepjarmu")) return false;
        Gepjarmu gepjarmu = (Gepjarmu) o;
        return Objects.equals(Rendszam.get(), gepjarmu.Rendszam.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rendszam);
    }

    @Override
    public String toString() {
        return "Gyártó: " + Gyarto + "\n" +
                "Típus: " + Tipus + "\n" +
                "Rendszám: " + Rendszam + "\n" +
                "Tulaj. neve: " + Nev + "\n" +
                "Tulaj. elérhetősége: " + Kontakt + "\n" +
                this.getKesz() + "\n";
    }
}