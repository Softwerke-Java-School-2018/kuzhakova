package ru.softwerke.catalog.model.comparators.invoice;

import ru.softwerke.catalog.model.entities.Client;
import ru.softwerke.catalog.model.entities.Invoice;

import java.util.Comparator;

public class ClientComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Invoice i1 = (Invoice)o1;
        Invoice i2 = (Invoice)o2;
        String s1 = i1.getClient().getLastName() + i1.getClient().getFirstName();
        String s2 = i2.getClient().getLastName() + i2.getClient().getFirstName();
        return s1.compareTo(s2);
    }
}

