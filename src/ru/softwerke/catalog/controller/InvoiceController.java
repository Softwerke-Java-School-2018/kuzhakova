package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.entities.Invoice;
import ru.softwerke.catalog.model.storing.Database;
import ru.softwerke.catalog.model.InvoiceModel;
import ru.softwerke.catalog.model.entities.Client;
import ru.softwerke.catalog.model.entities.Device;
import ru.softwerke.catalog.view.io.InputOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InvoiceController {
    private InvoiceModel invoiceModel = new InvoiceModel();
    private Database database = new Database();

    public InvoiceController() {
    }

    public boolean addInvoice(int idClient, int[] idDevices, int[] count, LocalDate dateSale) {
        //NullPointerException
        Client client = null;
        Device[] devices = new Device[idDevices.length];
        BigDecimal[] price = new BigDecimal[idDevices.length];
        BigDecimal sum = BigDecimal.valueOf(0);
        Map<Device, Integer> deviceCountMap = new HashMap<Device, Integer>();

        for (Client c : database.clientList()) {
            if (c.getID() == idClient) {
                client = c;
                break;
            }
        }
        int i = 0;
        for (int id : idDevices) {
            for (Device d : database.deviceList()) {
                if (d.getID() == id) {
                    devices[i] = d;
                    price[i] = d.getPrice();
                    i++;
                    break;
                }
            }
        }
        if (Objects.nonNull(client) && Objects.nonNull(devices[0])) {
            for (int j = 0; j < devices.length; j++) {
                sum = price[j].multiply(BigDecimal.valueOf(count[j]));
                deviceCountMap.put(devices[j], count[j]);
            }
            Invoice invoice = Invoice.newInvoiceBuilder()
                    .setClient(client)
                    .setDateSale(dateSale)
                    .setTotalSum(sum)
                    .setDeviceCountMap(deviceCountMap)
                    .build();
            return invoiceModel.addInvoice(invoice);
        }
        return false;
    }

    public String[] invoiceListToStringArray() {
        return invoiceModel.getStreamInvoiceList().map(c -> c.toString()).toArray(String[]::new);
    }

    public boolean deleteByID(int id) {
        try {
            Invoice invoice = invoiceModel.deleteByID(id);
            if (Objects.nonNull(invoice)) {
                InputOutput.printLine("The following device will be deleted:");
                InputOutput.printLine(invoice);
                return true;
            }
            return false;
        } catch (Exception e) {
            InputOutput.printLine(e.getMessage());
            return false;
        }
    }

    public boolean findByID(int id){
        for (Invoice invoice: invoiceModel.invoiceList()){
            if (invoice.getID() == id){
                InputOutput.printLine(invoice);
                return true;
            }
        }
        return false;
    }

    public int comparatorsCount() {
        return invoiceModel.getArrayComparatorsSize();
    }

    public void sort(String property, int sortingParameter) {
        List<Invoice> sortInvoiceList = invoiceModel.getStreamInvoiceList().collect(Collectors.toList());
        if (sortingParameter == 2) {
            Collections.sort(sortInvoiceList, Collections.reverseOrder(invoiceModel.getComparator(property)));
        } else {
            Collections.sort(sortInvoiceList, invoiceModel.getComparator(property));
        }
        for (Invoice invoice : sortInvoiceList) {
            InputOutput.printLine(invoice.toString());
        }
    }
}
