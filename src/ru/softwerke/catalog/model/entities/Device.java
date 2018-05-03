package ru.softwerke.catalog.model.entities;

import ru.softwerke.catalog.model.enums.*;
import ru.softwerke.catalog.view.io.InputOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Device extends DataItem {
    public static volatile AtomicInteger COUNT = new AtomicInteger(0);
    private String modelOfDevice;
    private Manufacturer manufacturer;
    private Color color;
    private LocalDate releaseDate;
    private DeviceType deviceType;
    private BigDecimal price;
    private static final String[] propertiesArray = {"manufacturer", "model", "date of release",
            "color", "type", "price"};

    public static String getPropertyInArray(int number) {
        try {
            return propertiesArray[number];
        } catch (ArrayIndexOutOfBoundsException e) {
            InputOutput.printLine("Invalid property number.");
            return "";
        }
    }

    public static String toStringPropertiesList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < propertiesArray.length; i++) {
            stringBuilder.append((i + 1) + ". " + propertiesArray[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private Device() {
    }

    public static DeviceBuilder newDeviceBuilder() {
        return new Device().new DeviceBuilder();
    }

    @Override
    public String toString() {
        return id + ": " + deviceType + " " + releaseDate + " " + price + " " + color + " "
                + manufacturer + " " + modelOfDevice;
    }

    public int getID() {
        return id;
    }

    public String getModelOfDevice() {
        return modelOfDevice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Color getColor() {
        return color;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Objects.isNull(o) || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(modelOfDevice, device.modelOfDevice) &&
                manufacturer == device.manufacturer &&
                color == device.color &&
                Objects.equals(releaseDate, device.releaseDate) &&
                deviceType == device.deviceType &&
                Objects.equals(price, device.price);
    }

    public class DeviceBuilder {

        private DeviceBuilder() {
        }

        public DeviceBuilder setId(int id) {
            Device.this.id = id;
            return this;
        }

        public DeviceBuilder setColor(Color color) {
            Device.this.color = color;
            return this;
        }

        public DeviceBuilder setModelOfDevice(String modelOfDevice) {
            Device.this.modelOfDevice = modelOfDevice;
            return this;
        }

        public DeviceBuilder setManufacturer(Manufacturer manufacturer) {
            Device.this.manufacturer = manufacturer;
            return this;
        }

        public DeviceBuilder setReleaseDate(LocalDate releaseDate) {
            Device.this.releaseDate = releaseDate;
            return this;
        }

        public DeviceBuilder setDeviceType(DeviceType deviceType) {
            Device.this.deviceType = deviceType;
            return this;
        }

        public DeviceBuilder setPrice(BigDecimal price) {
            Device.this.price = price;
            return this;
        }

        public Device build() {
            Device.this.id = COUNT.incrementAndGet();
            return Device.this;
        }

    }
}
