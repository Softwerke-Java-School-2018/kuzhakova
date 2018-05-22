package catalog.controller;

import catalog.model.dao.DeviceModel;
import catalog.model.entities.Device;
import catalog.model.enums.*;
import catalog.view.io.InputOutput;

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

    public void printList() {
        deviceModel.getStreamDeviceList().forEach(InputOutput::printLine);
    }

    public boolean addDevice(String modelOfDevice,
                             Manufacturer manufacturer,
                             Color color,
                             LocalDate releaseDate,
                             DeviceType deviceType,
                             BigDecimal price) {
        Device device = Device.newDeviceBuilder()
                //.setId()
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

    public boolean deleteByID(int id) {
        try {
            Device device = deviceModel.deleteByID(id);
            if (Objects.nonNull(device)) {
                InputOutput.printLine("The following device will be deleted:");
                InputOutput.printLine(device);
                return true;
            }
            return false;
        } catch (Exception e) {
            InputOutput.printLine(e.getMessage());
            return false;
        }
    }

    //TODO: ввод данных девайса при поиске и удалении
    public Device findDevice(String modelOfDevice,
                             Manufacturer manufacturer,
                             Color color,
                             LocalDate releaseDate,
                             DeviceType deviceType,
                             BigDecimal price) {
        Device c = deviceModel.equalsDevice(Device.newDeviceBuilder()
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
                deviceModel.deleteByID(ind);
                return true;
            }
            InputOutput.printLine("Device not found.");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public int comparatorsCount() {
        return deviceModel.getArrayComparatorsSize();
    }

    public void sort(String property, int sortingParameter) {
        List<Device> sorteDeviceList = deviceModel.getStreamDeviceList().collect(Collectors.toList());
        if (sortingParameter == 2) {
            Collections.sort(sorteDeviceList, Collections.reverseOrder(deviceModel.getComparator(property)));
        } else {
            Collections.sort(sorteDeviceList, deviceModel.getComparator(property));
        }
        for (Device device : sorteDeviceList) {
            InputOutput.printLine(device.toString());
        }
    }

    public void clearList(){
        deviceModel.clear();
    }

}
