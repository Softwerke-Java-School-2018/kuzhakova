package ru.softwerke.catalog.model.storing;

import ru.softwerke.catalog.model.entities.Client;
import ru.softwerke.catalog.model.entities.Device;
import ru.softwerke.catalog.model.entities.Invoice;

import java.util.ArrayList;
import java.util.List;

public class Database implements IDatabase {
    private volatile static List<Client> clientList = new ArrayList<>();
    private volatile static List<Device> deviceList = new ArrayList<>();
    private volatile static List<Invoice> invoiceList = new ArrayList<>();

    @Override
    public List<Client> clientList() {
        return clientList;
    }

    @Override
    public List<Device> deviceList() {
        return deviceList;
    }

    @Override
    public List<Invoice> invoiceList() {
        return invoiceList;
    }

}
