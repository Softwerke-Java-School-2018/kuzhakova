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

    public Device(String modelOfDevice,
                  Manufacturer manufacturer,
                  Color color,
                  LocalDate releaseDate,
                  DeviceType deviceType,
                  BigDecimal price) {
        this.id = ++COUNT;
        this.modelOfDevice = modelOfDevice;
        this.manufacturer = manufacturer;
        this.color = color;
        this.releaseDate = releaseDate;
        this.deviceType = deviceType;
        this.price = price;
    }

    public Device(int id, String modelOfDevice,
                  Manufacturer manufacturer,
                  Color color,
                  LocalDate releaseDate,
                  DeviceType deviceType,
                  BigDecimal price) {
        this.id = id;
        this.modelOfDevice = modelOfDevice;
        this.manufacturer = manufacturer;
        this.color = color;
        this.releaseDate = releaseDate;
        this.deviceType = deviceType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Device:" + deviceType + " " + manufacturer + " " + modelOfDevice + " " + color;
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

    public void setModelOfDevice(String modelOfDevice) {
        this.modelOfDevice = modelOfDevice;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
