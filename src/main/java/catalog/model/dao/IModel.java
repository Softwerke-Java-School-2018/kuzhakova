package catalog.model.dao;


import catalog.model.entities.Client;
import catalog.model.entities.Device;
import catalog.model.entities.Invoice;

import java.util.List;

public interface IModel {
    List<Client> clientList();

    List<Device> deviceList();

    List<Invoice> invoiceList();
}
