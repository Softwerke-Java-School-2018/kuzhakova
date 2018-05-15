package catalog.model.comparators.client;

import catalog.model.entities.Client;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Client c1 = (Client)o1;
        Client c2 = (Client)o2;
        return c1.getFirstName().compareTo(c2.getFirstName());
    }
}