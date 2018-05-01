package ru.softwerke.catalog.model.storing;


import ru.softwerke.catalog.model.entities.Client;
import ru.softwerke.catalog.model.entities.Device;
import ru.softwerke.catalog.model.entities.Invoice;

import java.util.List;

public interface IDatabase {
    List<Client> clientList();

    List<Device> deviceList();

    List<Invoice> invoiceList();
}
