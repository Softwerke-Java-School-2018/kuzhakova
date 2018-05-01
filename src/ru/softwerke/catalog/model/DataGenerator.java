package ru.softwerke.catalog.model;

import ru.softwerke.catalog.model.entities.Client;
import ru.softwerke.catalog.model.entities.Device;
import ru.softwerke.catalog.model.entities.Invoice;
import ru.softwerke.catalog.model.enums.Color;
import ru.softwerke.catalog.model.enums.DeviceType;
import ru.softwerke.catalog.model.enums.Manufacturer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class DataGenerator {
    private ClientModel clientModel = new ClientModel();
    private DeviceModel deviceModel = new DeviceModel();
    private InvoiceModel invoiceModel = new InvoiceModel();

    public void generateData() {
        Client client = Client.newClientBuilder()
                .setId()
                .setFirstName("Petr")
                .setLastName("Petrov")
                .setBirthDate(LocalDate.of(1992, 12, 2))
                .build();
        clientModel.addClient(client);
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("Mariah")
                .setLastName("Carey")
                .setBirthDate(LocalDate.of(1972, 10, 2))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("Nikola")
                .setLastName("Tesla")
                .setBirthDate(LocalDate.of(1955, 5, 13))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("Stephen")
                .setLastName("Hawking")
                .setBirthDate(LocalDate.of(1942, 11, 2))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("Steven")
                .setLastName("Spielberg")
                .setBirthDate(LocalDate.of(1991, 1, 7))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("Christopher")
                .setLastName("Nolan")
                .setBirthDate(LocalDate.of(1961, 1, 5))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("Niels")
                .setLastName("Bohr")
                .setBirthDate(LocalDate.of(1971, 10, 3))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setId()
                .setFirstName("James")
                .setLastName("Cameron")
                .setBirthDate(LocalDate.of(1981, 12, 7))
                .build());
        Device device = Device.newDeviceBuilder()
                .setId()
                .setModelOfDevice("Redmi 3S")
                .setManufacturer(Manufacturer.XIAOMI)
                .setColor(Color.BLACK)
                .setReleaseDate(LocalDate.of(2016, 5, 1))
                .setDeviceType(DeviceType.PHONE)
                .setPrice(BigDecimal.valueOf(10000.0))
                .build();
        deviceModel.addDevice(device);
        deviceModel.addDevice(Device.newDeviceBuilder()
                .setId()
                .setModelOfDevice("Galaxy 5")
                .setManufacturer(Manufacturer.SAMSUNG)
                .setColor(Color.PINK)
                .setReleaseDate(LocalDate.of(2017, 5, 1))
                .setDeviceType(DeviceType.PHONE)
                .setPrice(BigDecimal.valueOf(20000.0))
                .build());
        deviceModel.addDevice(Device.newDeviceBuilder()
                .setId()
                .setModelOfDevice("Galaxy A7")
                .setManufacturer(Manufacturer.SAMSUNG)
                .setColor(Color.PINK)
                .setReleaseDate(LocalDate.of(2017, 5, 1))
                .setDeviceType(DeviceType.PHONE)
                .setPrice(BigDecimal.valueOf(28000.0))
                .build());
        Map<Device, Integer> deviceCountMap = new HashMap<>();
        deviceCountMap.put(device, 2);
        invoiceModel.addInvoice(Invoice.newInvoiceBuilder()
                .setId()
                .setDateSale(LocalDate.of(2018, 2, 1))
                .setClient(client)
                .setDeviceCountMap(deviceCountMap)
                .build());
    }

}