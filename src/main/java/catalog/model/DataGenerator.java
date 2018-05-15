package catalog.model;

import catalog.model.entities.Client;
import catalog.model.entities.Device;
import catalog.model.entities.Invoice;
import catalog.model.enums.Color;
import catalog.model.enums.DeviceType;
import catalog.model.enums.Manufacturer;

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
                .setFirstName("Petr")
                .setLastName("Petrov")
                .setBirthDate(LocalDate.of(1992, 12, 2))
                .build();
        clientModel.addClient(client);
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("Mariah")
                .setLastName("Carey")
                .setBirthDate(LocalDate.of(1972, 10, 2))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("Nikola")
                .setLastName("Tesla")
                .setBirthDate(LocalDate.of(1955, 5, 13))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("Stephen")
                .setLastName("Hawking")
                .setBirthDate(LocalDate.of(1942, 11, 2))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("Steven")
                .setLastName("Spielberg")
                .setBirthDate(LocalDate.of(1991, 1, 7))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("Christopher")
                .setLastName("Nolan")
                .setBirthDate(LocalDate.of(1961, 1, 5))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("Niels")
                .setLastName("Bohr")
                .setBirthDate(LocalDate.of(1971, 10, 3))
                .build());
        clientModel.addClient(Client.newClientBuilder()
                .setFirstName("James")
                .setLastName("Cameron")
                .setBirthDate(LocalDate.of(1981, 12, 7))
                .build());
        Device device = Device.newDeviceBuilder()
                .setModelOfDevice("Redmi 3S")
                .setManufacturer(Manufacturer.XIAOMI)
                .setColor(Color.BLACK)
                .setReleaseDate(LocalDate.of(2016, 5, 1))
                .setDeviceType(DeviceType.PHONE)
                .setPrice(BigDecimal.valueOf(10000.0))
                .build();
        deviceModel.addDevice(device);
        deviceModel.addDevice(Device.newDeviceBuilder()
                .setModelOfDevice("Galaxy 5")
                .setManufacturer(Manufacturer.SAMSUNG)
                .setColor(Color.PINK)
                .setReleaseDate(LocalDate.of(2017, 5, 1))
                .setDeviceType(DeviceType.PHONE)
                .setPrice(BigDecimal.valueOf(20000.0))
                .build());
        deviceModel.addDevice(Device.newDeviceBuilder()
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
                .setDateSale(LocalDate.of(2018, 2, 1))
                .setClient(client)
                .setDeviceCountMap(deviceCountMap)
                .build());
    }

}
