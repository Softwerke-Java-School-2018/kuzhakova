package ru.softwerke.catalog.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Check {
    private static int COUNT = 0;
    private int id;
    private int idClient;
    private LocalDate dateSale;
    private BigDecimal totalSum;

    private Check() {
    }

    public static CheckBuilder newCheckBuilder() {
        return new Check().new CheckBuilder();
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

    public class CheckBuilder {

        private CheckBuilder() {
        }

        public CheckBuilder setId(int id) {
            Check.this.id = id;
            return this;
        }

        public CheckBuilder setId() {
            Check.this.id = ++COUNT;
            return this;
        }

        public CheckBuilder setIdClient(int idClient) {
            Check.this.idClient = idClient;
            return this;
        }

        public CheckBuilder setDateSale(LocalDate dateSale) {
            Check.this.dateSale = dateSale;
            return this;
        }

        public CheckBuilder setTotalSum(BigDecimal totalSum) {
            Check.this.totalSum = totalSum;
            return this;
        }

        public Check build() {
            return Check.this;
        }

    }
}
