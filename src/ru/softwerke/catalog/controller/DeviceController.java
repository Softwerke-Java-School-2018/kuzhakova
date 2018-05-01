package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.DeviceModel;
import ru.softwerke.catalog.model.entities.Device;
import ru.softwerke.catalog.model.enums.*;
import ru.softwerke.catalog.view.io.InputOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeviceController {
    private DeviceModel deviceModel = new DeviceModel();

    public DeviceController() {
    }

    public String[] deviceListToStringArray() {
        return deviceModel.getStreamDeviceList().map(d -> d.toString()).toArray(String[]::new);
    }

    public boolean addDevice(String modelOfDevice,
                             Manufacturer manufacturer,
                             Color color,
                             LocalDate releaseDate,
                             DeviceType deviceType,
                             BigDecimal price) {
        Device device = Device.newDeviceBuilder()
                .setId()
                .setModelOfDevice(modelOfDevice)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setReleaseDate(releaseDate)
                .setDeviceType(deviceType)
                .setPrice(price)
                .build();
        return deviceModel.addDevice(device);
    }

    public boolean deleteDevice(String modelOfDevice,
                                Manufacturer manufacturer,
                                Color color,
                                LocalDate releaseDate,
                                DeviceType deviceType,
                                BigDecimal price) {
        Device device = Device.newDeviceBuilder()
                .setId(0)
                .setModelOfDevice(modelOfDevice)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setReleaseDate(releaseDate)
                .setDeviceType(deviceType)
                .setPrice(price)
                .build();
        return deviceModel.deleteDevice(device);
    }

    //TODO: ввод данных девайса при поиске и удалении
    public Device findDevice(String modelOfDevice,
                             Manufacturer manufacturer,
                             Color color,
                             LocalDate releaseDate,
                             DeviceType deviceType,
                             BigDecimal price) {
        Device c = deviceModel.equalsDevice(Device.newDeviceBuilder()
                .setId()
                .setModelOfDevice(modelOfDevice)
                .setManufacturer(manufacturer)
                .setColor(color)
                .setReleaseDate(releaseDate)
                .setDeviceType(deviceType)
                .setPrice(price)
                .build());
        if (Objects.nonNull(c)) InputOutput.printLine(c.toString());
        return c;
    }

    public boolean findSimilarDevices(String modelOfDevice,
                                      Manufacturer manufacturer,
                                      Color color,
                                      LocalDate releaseDate,
                                      DeviceType deviceType,
                                      BigDecimal price) {
        try {
            Stream<Device> deviceStream = deviceModel.selectDevices(modelOfDevice, manufacturer, color,
                    releaseDate, deviceType, price);
            List<Device> devices = deviceStream.collect(Collectors.toList());
            if (!devices.isEmpty()) {
                InputOutput.printLine("The following devices are found for this query:");
                devices.forEach(InputOutput::printLine);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean printFoundDeviceList(String modelOfDevice,
                                        Manufacturer manufacturer,
                                        Color color,
                                        LocalDate releaseDate,
                                        DeviceType deviceType,
                                        BigDecimal price) {
        try {
            if (findSimilarDevices(modelOfDevice, manufacturer, color,
                    releaseDate, deviceType, price)) {
                InputOutput.printLine("Enter ID to delete client. Enter 0 to cancel:");
                int ind = InputOutput.readInt();
                if (ind == 0) return false;
                deviceModel.deleteDevice(ind);
                return true;
            }
            InputOutput.printLine("Device not found.");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void sort(int what, int how) {
        List<Device> sortedDeviceList = deviceModel.getStreamDeviceList().collect(Collectors.toList());
        if (how == 2) {
            Collections.sort(sortedDeviceList, Collections.reverseOrder(deviceModel.getComparator(what - 1)));
        } else {
            Collections.sort(sortedDeviceList, deviceModel.getComparator(what - 1));
        }
        for (Device c : sortedDeviceList) {
            InputOutput.printLine(c.toString());
        }
    }
}
