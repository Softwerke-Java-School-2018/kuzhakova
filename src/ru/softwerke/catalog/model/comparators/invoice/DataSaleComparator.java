package ru.softwerke.catalog.model.comparators.invoice;

import ru.softwerke.catalog.model.entities.Invoice;

import java.util.Comparator;

public class DataSaleComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Invoice i1 = (Invoice)o1;
        Invoice i2 = (Invoice)o2;
        return i1.getDateSale().compareTo(i2.getDateSale());
    }
}
