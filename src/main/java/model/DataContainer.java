package model;

public final class DataContainer {
    private Gepjarmu gepjarmu;

    private final static DataContainer INSTANCE = new DataContainer();

    private DataContainer() { }

    public Gepjarmu getGepjarmu() {
        return gepjarmu;
    }

    public void setGepjarmu(Gepjarmu gepjarmu) {
        this.gepjarmu = gepjarmu;
    }

    public static DataContainer getINSTANCE() {
        return INSTANCE;
    }
}
