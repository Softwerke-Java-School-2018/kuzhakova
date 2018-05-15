package catalog.model.comparators.device;

import catalog.model.entities.Device;

import java.util.Comparator;

public class ColorComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Device d1 = (Device)o1;
        Device d2 = (Device)o2;
        return d1.getColor().compareTo(d2.getColor());
    }
}