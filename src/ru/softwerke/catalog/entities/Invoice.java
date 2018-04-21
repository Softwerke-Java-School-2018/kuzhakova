package ru.softwerke.catalog.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {
    private static int COUNT = 0;
    private int id;
    private int idClient;
    private LocalDate dateSale;
    private BigDecimal totalSum;

    private Invoice() {
    }

    public static InvoiceBuilder newInvoiceBuilder() {
        return new Invoice().new InvoiceBuilder();
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public LocalDate getDateSale() {
        return dateSale;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public class InvoiceBuilder {

        private InvoiceBuilder() {
        }

        public InvoiceBuilder setId(int id) {
            Invoice.this.id = id;
            return this;
        }

        public InvoiceBuilder setId() {
            Invoice.this.id = ++COUNT;
            return this;
        }

        public InvoiceBuilder setIdClient(int idClient) {
            Invoice.this.idClient = idClient;
            return this;
        }

        public InvoiceBuilder setDateSale(LocalDate dateSale) {
            Invoice.this.dateSale = dateSale;
            return this;
        }

        public InvoiceBuilder setTotalSum(BigDecimal totalSum) {
            Invoice.this.totalSum = totalSum;
            return this;
        }

        public Invoice build() {
            return Invoice.this;
        }

    }
}
