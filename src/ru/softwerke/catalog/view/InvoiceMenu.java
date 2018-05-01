package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.InvoiceController;
import ru.softwerke.catalog.view.io.IOUtils;
import ru.softwerke.catalog.view.io.InputOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceMenu extends MainMenu {
    InvoiceController invoiceController = new InvoiceController();
    private int id;
    private int idClient;
    int count;
    int idDevice;
    private LocalDate dateSale;
    private BigDecimal totalSum;
    DateTimeFormatter formatter;

    public static String MENU_INVOICE = "Invoices:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete invoice\n" +
            "4. Find invoice\n" +
            "5. Sort invoice\n" +
            "0. Back";
    public static InvoiceMenu instance;

    private InvoiceMenu() {
    }

    public static InvoiceMenu getInstance() {
        if (instance == null)
            synchronized (InvoiceMenu.class) {
                if (instance == null)
                    instance = new InvoiceMenu();
            }
        return instance;
    }

    public void menuInvoices() {
        String choice;
        do {
            InputOutput.printLine(MENU_INVOICE);
            choice = InputOutput.readLine();
            switch (choice) {
                case "1":
                    menuPrintInvoices();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    break;
                default:
                    break;
            }

        } while (!choice.equals("0"));
    }

    public void enterData() {
        InputOutput.printLine("Enter id of client:");
        idClient = InputOutput.readInt();
        InputOutput.printLine("Enter date of sale (dd/mm/yyyy):");
        String enterSaleDate = InputOutput.readLine();
        boolean isEmptyDate = "".equals(enterSaleDate);
        if (!isEmptyDate) {
            while (!IOUtils.checkEnterDateWithRegExp(enterSaleDate)) {
                InputOutput.printLine("Wrong enter! Enter date:");
                enterSaleDate = InputOutput.readLine();
            }
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateSale = LocalDate.parse(enterSaleDate, formatter);
        } else dateSale = LocalDate.MAX;

        InputOutput.printLine("Enter id of device:");
        idDevice = InputOutput.readInt();
        InputOutput.printLine("Enter count of device:");
        count = InputOutput.readInt();
    }

    public void menuPrintInvoices() {
        String[] invoices = invoiceController.invoiceListToStringArray();
        for (String i : invoices) {
            InputOutput.printLine(i);
        }
    }

    public void menuAddInvoice() {
        enterData();
        /*if (invoiceController.addInvoice(idClient, idDevice, count, dateSale)) {
            InputOutput.printLine("Invoice successfully added.");
        } else InputOutput.printLine("Invalid input. The information was not completely entered.");*/
    }
}
