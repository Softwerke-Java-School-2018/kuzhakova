package ru.softwerke.catalog.model.comparators.device;

import ru.softwerke.catalog.model.entities.Device;

import java.util.Comparator;

public class ModelComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Device d1 = (Device) o1;
        Device d2 = (Device) o2;
        return d1.getModelOfDevice().compareTo(d2.getModelOfDevice());
    }
}