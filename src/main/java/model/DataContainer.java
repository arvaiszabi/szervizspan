package model;

public final class DataContainer {
    private Gepjarmu gepjarmu;

    private static final DataContainer INSTANCE = new DataContainer();

    private DataContainer() { }

    public Gepjarmu getGepjarmu() {
        return gepjarmu;
    }

    public static DataContainer getINSTANCE() {
        return INSTANCE;
    }

    public void setGepjarmu(Gepjarmu gepjarmu) {
        this.gepjarmu = gepjarmu;
    }
}
