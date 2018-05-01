package ru.softwerke.catalog.model;

import ru.softwerke.catalog.model.storing.Database;
import ru.softwerke.catalog.model.entities.Invoice;

import java.util.stream.Stream;

public class InvoiceModel extends Database {
    //private List<Invoice> invoiceList = invoiceList();

    public Stream<Invoice> getStreamInvoiceList() {
        return invoiceList().stream();
    }

    public boolean addInvoice(Invoice invoice) {
        return invoiceList().add(invoice);
    }

    public boolean deleteInvoice(Invoice invoice) {
        return invoiceList().remove(invoice);
    }
}
