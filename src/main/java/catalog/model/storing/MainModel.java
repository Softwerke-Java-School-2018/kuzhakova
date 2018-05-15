package catalog.model.storing;

import catalog.model.entities.Client;
import catalog.model.entities.Device;
import catalog.model.entities.Invoice;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements IModel {
    private volatile static List<Client> clientList = new ArrayList<Client>();
    private volatile static List<Device> deviceList = new ArrayList<Device>();
    private volatile static List<Invoice> invoiceList = new ArrayList<Invoice>();

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
