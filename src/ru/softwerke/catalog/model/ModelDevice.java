package ru.softwerke.catalog.Model;

import ru.softwerke.catalog.entities.Device;

import java.util.ArrayList;
import java.util.List;

public class ModelDevice {
    private List<Device> deviceList = new ArrayList<>();

    public List<Device> getDeviceList() {
        return deviceList;
    }
}
