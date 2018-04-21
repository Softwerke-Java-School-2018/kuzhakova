package ru.softwerke.catalog.entities;

public class InvoiceLine {
    private int idClient;
    private int idDevice;
    private int count;

    private InvoiceLine() {
    }

    public static InvoiceLineBuilder newInvoiceLineBuilder() {
        return new InvoiceLine().new InvoiceLineBuilder();
    }

    public class InvoiceLineBuilder {

        private InvoiceLineBuilder() {
        }

        public InvoiceLineBuilder setIdClient(int idClient) {
            InvoiceLine.this.idClient = idClient;
            return this;
        }

        public InvoiceLineBuilder setIdDevice(int idDevice) {
            InvoiceLine.this.idDevice = idDevice;
            return this;
        }

        public InvoiceLineBuilder setCount(int count) {
            InvoiceLine.this.count = count;
            return this;
        }

        public InvoiceLine build() {
            return InvoiceLine.this;
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
