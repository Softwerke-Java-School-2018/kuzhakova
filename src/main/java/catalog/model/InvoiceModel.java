package catalog.model;

import catalog.model.comparators.invoice.*;
import catalog.model.storing.MainModel;
import catalog.model.entities.Invoice;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class InvoiceModel extends MainModel {
    private static final Map<String, Comparator<Object>> comparatorsMap =
            Collections.unmodifiableMap(new HashMap<String, Comparator<Object>>() {{
                put("client", new ClientComparator());
                put("date of sale", new DataSaleComparator());
                put("total sum", new TotalSumComparator());
            }});

    public Stream<Invoice> getStreamInvoiceList() {
        return invoiceList().stream();
    }

    public Comparator getComparator(String property) {
        return comparatorsMap.get(property);
    }

    public boolean addInvoice(Invoice invoice) {
        return invoiceList().add(invoice);
    }

    public boolean deleteInvoice(Invoice invoice) {
        return invoiceList().remove(invoice);
    }

    public Invoice deleteByID(int id) {
        int index = -1;
        for (Invoice invoice: invoiceList()) {
            if (invoice.getID() == id) {
                index = invoiceList().indexOf(invoice);
            }
        }
        if (index == -1) {
            return null;
        }
        return invoiceList().remove(index);
    }

    public int getArrayComparatorsSize() {
        return comparatorsMap.size();
    }
}
