package ru.softwerke.catalog.entities;

import ru.softwerke.catalog.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Device {
    private int id;
    private static int COUNT = 0;
    private String modelOfDevice;
    private Manufacturer manufacturer;
    private Color color;
    private LocalDate releaseDate;
    private DeviceType deviceType;
    private BigDecimal price;

    private Device() {
    }

    public static DeviceBuilder newDeviceBuilder() {
        return new Device().new DeviceBuilder();
    }

    @Override
    public String toString() {
        return "Device: " + deviceType + " " + manufacturer + " " + modelOfDevice + " " + color;
    }

    public int getId() {
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
        if (o == null || getClass() != o.getClass()) return false;
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

        public DeviceBuilder setId() {
            Device.this.id = ++COUNT;
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
            return Device.this;
        }

    }
}
