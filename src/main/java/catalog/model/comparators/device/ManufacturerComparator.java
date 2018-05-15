package catalog.model.comparators.device;

import catalog.model.entities.Device;

import java.util.Comparator;

public class ManufacturerComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Device d1 = (Device) o1;
        Device d2 = (Device) o2;
       /* String s1 = d1.getManufacturer() + d1.getModelOfDevice();
        String s2 = d2.getManufacturer() + d2.getModelOfDevice();*/
        return d1.getManufacturer().compareTo(d2.getManufacturer());
    }
}
