package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelInvoiceLine;
import ru.softwerke.catalog.entities.InvoiceLine;

public class ControllerInvoiceLine {
    ModelInvoiceLine modelInvoiceLine = new ModelInvoiceLine();

    public boolean addInvoiceLine(int idClient, int idDevice, int count) {
        try {
            InvoiceLine invoiceLine = InvoiceLine.newInvoiceLineBuilder()
                    .setIdDevice(idDevice)
                    .setIdClient(idClient)
                    .setCount(count)
                    .build();
            modelInvoiceLine.getInvoiceLines().add(invoiceLine);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
