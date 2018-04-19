package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelDevice;
import ru.softwerke.catalog.entities.Device;
import ru.softwerke.catalog.enums.*;
import ru.softwerke.catalog.view.InputOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class ControllerDevice {
    ModelDevice modelDevice = new ModelDevice();

    public ControllerDevice() {
        addDevice("3S", Manufacturer.XIAOMI, Color.BLACK, LocalDate.of(2016, 5, 1), DeviceType.PHONE, BigDecimal.valueOf(10000.0));
        addDevice("Galaxy 5", Manufacturer.SAMSUNG, Color.PINK, LocalDate.of(2017, 5, 1), DeviceType.PHONE, BigDecimal.valueOf(20000.0));
    }

    public void printDeviceList() {

    }

    public boolean addDevice(String modelOfDevice,
                             Manufacturer manufacturer,
                             Color color,
                             LocalDate releaseDate,
                             DeviceType deviceType,
                             BigDecimal price) {
        try {
            modelDevice.getDeviceList().add(new Device(modelOfDevice, manufacturer, color, releaseDate, deviceType, price));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteDevice(String modelOfDevice,
                                Manufacturer manufacturer,
                                Color color,
                                LocalDate releaseDate,
                                DeviceType deviceType,
                                BigDecimal price) {
        try {
            return modelDevice.getDeviceList().remove(new Device(0, modelOfDevice, manufacturer, color, releaseDate, deviceType, price));
        } catch (Exception e) {
            InputOutput.printLine("Could not remove device.");
            return false;
        }
    }
}
