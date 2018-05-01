package ru.softwerke.catalog.model;

import ru.softwerke.catalog.model.storing.Database;
import ru.softwerke.catalog.model.entities.Device;
import ru.softwerke.catalog.model.enums.Color;
import ru.softwerke.catalog.model.enums.DeviceType;
import ru.softwerke.catalog.model.enums.Manufacturer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DeviceModel extends Database {
   // private List<Device> deviceList = deviceList();
    private List<Comparator<Device>> arrayComparators = new ArrayList<>();

    public DeviceModel() {
        arrayComparators.add(new ModelOfDeviceComparator());
        arrayComparators.add(new ManufacturerComparator());
        arrayComparators.add(new ColorComparator());
        arrayComparators.add(new ReleaseDateComparator());
        arrayComparators.add(new DeviceTypeComparator());
        arrayComparators.add(new PriceComparator());
    }

    public Comparator getComparator(int n) {
        return arrayComparators.get(n);
    }

    public class ModelOfDeviceComparator implements Comparator<Device> {
        @Override
        public int compare(Device o1, Device o2) {
            return o1.getModelOfDevice().compareTo(o2.getModelOfDevice());
        }
    }

    public class ManufacturerComparator implements Comparator<Device> {
        @Override
        public int compare(Device o1, Device o2) {
            return o1.getManufacturer().compareTo(o2.getManufacturer());
        }
    }

    public class ColorComparator implements Comparator<Device> {
        @Override
        public int compare(Device o1, Device o2) {
            return o1.getColor().compareTo(o2.getColor());
        }
    }

    public class ReleaseDateComparator implements Comparator<Device> {
        @Override
        public int compare(Device o1, Device o2) {
            return o1.getReleaseDate().compareTo(o2.getReleaseDate());
        }
    }

    public class DeviceTypeComparator implements Comparator<Device> {
        @Override
        public int compare(Device o1, Device o2) {
            return o1.getDeviceType().compareTo(o2.getDeviceType());
        }
    }

    public class PriceComparator implements Comparator<Device> {
        @Override
        public int compare(Device o1, Device o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
    }

    public Stream<Device> getStreamDeviceList() {
        return deviceList().stream();
    }

    public Stream<Device> selectDevices(String modelOfDevice,
                                        Manufacturer manufacturer,
                                        Color color,
                                        LocalDate releaseDate,
                                        DeviceType deviceType,
                                        BigDecimal price) {
        List<Device> selectedDeviceList = new ArrayList<Device>(deviceList());
        selectedDeviceList.removeIf(d ->
                !(modelOfDevice.equals(d.getModelOfDevice()) || manufacturer.equals(d.getManufacturer()) ||
                        color.equals(d.getColor()) || releaseDate.isEqual(d.getReleaseDate()) ||
                        deviceType.equals(d.getDeviceType()) || price.equals(d.getPrice())));
        if (selectedDeviceList.isEmpty()) return Stream.empty();
        return selectedDeviceList.stream();
    }

    public boolean addDevice(Device device) {
        return deviceList().add(device);
    }

    public boolean deleteDevice(Device device) {
        return deviceList().remove(device);
    }

    public boolean deleteDevice(int id) {
        return deviceList().removeIf(d -> d.getId() == id);
    }

    public Device equalsDevice(Device enteredClient) {
        for (Device d : deviceList()) {
            if (d.equals(enteredClient)) return d;
        }
        return null;
    }
}
