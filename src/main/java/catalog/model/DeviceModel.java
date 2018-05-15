package catalog.model;

import catalog.model.comparators.device.*;
import catalog.model.storing.MainModel;
import catalog.model.entities.Device;
import catalog.model.enums.Color;
import catalog.model.enums.DeviceType;
import catalog.model.enums.Manufacturer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class DeviceModel extends MainModel {
    private static final Map<String, Comparator<Object>> comparatorsMap =
            Collections.unmodifiableMap(new HashMap<String, Comparator<Object>>() {{
                put("manufacturer", new ManufacturerComparator());
                put("model", new ModelComparator());
                put("date of release", new ReleaseDateComparator());
                put("color", new ColorComparator());
                put("type", new DeviceTypeComparator());
                put("price", new PriceComparator());
            }});

    public DeviceModel() {
    }

    public int getArrayComparatorsSize() {
        return comparatorsMap.size();
    }

    public Comparator getComparator(String property) {
        return comparatorsMap.get(property);
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

    public Device deleteByID(int id) {
        int index = -1;
        for (Device d : deviceList()) {
            if (d.getID() == id) {
                index = deviceList().indexOf(d);
            }
        }
        if (index == -1) {
            return null;
        }
        return deviceList().remove(index);
    }

    public Device equalsDevice(Device enteredClient) {
        for (Device d : deviceList()) {
            if (d.equals(enteredClient)) return d;
        }
        return null;
    }
}
