package ru.softwerke.catalog.model;

import ru.softwerke.catalog.entities.Invoice;

import java.util.ArrayList;
import java.util.List;

public class ModelInvoice {
    private List<Invoice> invoiceList = new ArrayList<Invoice>();

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }
}
