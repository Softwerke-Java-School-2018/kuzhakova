package ru.softwerke.catalog.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Check {
    private static int COUNT = 0;
    private int id;
    private int idClient;
    private LocalDate dateSale;
    private BigDecimal totalSum;

    public Check(LocalDate dateSale, BigDecimal totalSum) {
        this.idClient = idClient;
        this.dateSale = dateSale;
        this.totalSum = totalSum;
    }

    public Check(int idClient, LocalDate dateSale, BigDecimal totalSum) {
        this.id = ++COUNT;
        this.idClient = idClient;
        this.dateSale = dateSale;
        this.totalSum = totalSum;
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

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setDateSale(LocalDate dateSale) {
        this.dateSale = dateSale;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }
}
