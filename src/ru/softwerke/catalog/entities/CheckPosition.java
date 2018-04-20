package ru.softwerke.catalog.entities;

public class CheckPosition {
    private int idClient;
    private int idDevice;
    private int count;

    private CheckPosition() {
    }

    public static CheckPositionBuilder newCheckPositionBuilder() {
        return new CheckPosition().new CheckPositionBuilder();
    }

    public class CheckPositionBuilder {

        private CheckPositionBuilder() {
        }

        public CheckPositionBuilder setIdClient(int idClient) {
            CheckPosition.this.idClient = idClient;
            return this;
        }

        public CheckPositionBuilder setIdDevice(int idDevice) {
            CheckPosition.this.idDevice = idDevice;
            return this;
        }

        public CheckPositionBuilder setCount(int count) {
            CheckPosition.this.count = count;
            return this;
        }

        public CheckPosition build() {
            return CheckPosition.this;
        }

    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public int getCount() {
        return count;
    }

}
