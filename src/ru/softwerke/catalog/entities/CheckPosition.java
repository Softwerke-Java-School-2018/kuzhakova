package ru.softwerke.catalog.entities;

public class CheckPosition {
    private static int COUNT = 0;
    private int idClient;
    private int idDevice;
    private int numberOf;

    public CheckPosition(int idDevice, int numberOf) {
        this.idClient = ++COUNT;
        this.idDevice = idDevice;
        this.numberOf = numberOf;
    }

    public CheckPosition(int idClient, int idDevice, int numberOf) {
        this.idClient = idClient;
        this.idDevice = idDevice;
        this.numberOf = numberOf;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }
}
