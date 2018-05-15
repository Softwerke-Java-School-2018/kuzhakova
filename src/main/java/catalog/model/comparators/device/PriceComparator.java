package catalog.model.comparators.device;

import catalog.model.entities.Device;

import java.util.Comparator;

public class PriceComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Device d1 = (Device)o1;
        Device d2 = (Device)o2;
        return d1.getPrice().compareTo(d2.getPrice());
    }
}
