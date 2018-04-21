package ru.softwerke.catalog.model;

import ru.softwerke.catalog.entities.InvoiceLine;

import java.util.ArrayList;
import java.util.List;

public class ModelInvoiceLine {
    private List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

}
