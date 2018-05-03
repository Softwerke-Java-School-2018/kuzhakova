package ru.softwerke.catalog.model.entities;

import javafx.scene.chart.PieChart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Invoice extends DataItem {
    public static volatile AtomicInteger COUNT = new AtomicInteger(0);
    private Client client;
    private LocalDate dateSale;
    private BigDecimal totalSum;
    private Map<Device, Integer> deviceCountMap = new HashMap<Device, Integer>();
    private static final String[] propertiesArray = {"client", "date of sale", "total sum"};

    private Invoice() {
    }

    public static String getPropertyInArray(int number) {
        return propertiesArray[number];
    }

    public static String toStringPropertiesList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < propertiesArray.length; i++) {
            stringBuilder.append((i + 1) + ". " + propertiesArray[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private void calculateTotalSum() {
        totalSum = BigDecimal.valueOf(0);
        Device device;
        int count;
        BigDecimal price;
        for (Map.Entry entry : deviceCountMap.entrySet()) {
            device = (Device) entry.getKey();
            count = (int) entry.getValue();
            price = device.getPrice();
            totalSum = totalSum.add(price.multiply(BigDecimal.valueOf(count)));
        }
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public static InvoiceBuilder newInvoiceBuilder() {
        return new Invoice().new InvoiceBuilder();
    }

    public int getID() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void addInvoiceLine(Device device, int count) {
        deviceCountMap.put(device, count);
    }

    public LocalDate getDateSale() {
        return dateSale;
    }

    public Map<Device, Integer> getDeviceCountMap() {
        return deviceCountMap;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(id)
                .append(" : ")
                .append(client.getFirstName() + " " + client.getLastName())
                .append(" ")
                .append(dateSale.toString())
                .append(", devices:")
                .append(System.lineSeparator());
        deviceCountMap.forEach((d, c) -> {
                    stringBuilder.append(d.getDeviceType() + " " + d.getColor() + " " + d.getManufacturer() + " "
                            + d.getModelOfDevice())
                            .append(" ")
                            .append(" - " + c)
                            .append(System.lineSeparator());
                    ;
                }
        );
        stringBuilder.append("Total sum: " + totalSum);
        return stringBuilder.toString();
    }

    public class InvoiceBuilder {

        private InvoiceBuilder() {
        }

        public InvoiceBuilder setId(int id) {
            Invoice.this.id = id;
            return this;
        }

        public InvoiceBuilder setTotalSum(BigDecimal totalSum) {
            Invoice.this.totalSum = totalSum;
            return this;
        }

        public InvoiceBuilder setClient(Client client) {
            Invoice.this.client = client;
            return this;
        }

        public InvoiceBuilder setDateSale(LocalDate dateSale) {
            Invoice.this.dateSale = dateSale;
            return this;
        }

        public InvoiceBuilder setDeviceCountMap(Map<Device, Integer> deviceCountMap) {
            Invoice.this.deviceCountMap = deviceCountMap;
            return this;
        }

        public Invoice build() {
            Invoice.this.id = COUNT.incrementAndGet();
            calculateTotalSum();
            return Invoice.this;
        }

    }
}
