package ru.softwerke.catalog.view;

import ru.softwerke.catalog.view.io.InputOutput;

public class MainMenu {
    ClientMenu clientMenu;
    DeviceMenu deviceMenu;
    InvoiceMenu invoiceMenu;

    public final String MAIN_MENU = "Menu\n" +
            "1. Clients\n" +
            "2. Devices\n" +
            "3. Invoices\n" +
            "0. Exit\n";
    public final String MENU_HOW_SORT = "Sort:\n" +
            "1. Ascending\n" +
            "2. Descending\n" +
            "0. Back";

    public void menu() {
        clientMenu = ClientMenu.getInstance();
        deviceMenu = DeviceMenu.getInstance();
        invoiceMenu = InvoiceMenu.getInstance();
        String choice;
        do {
            InputOutput.printLine(MAIN_MENU);
            choice = InputOutput.readLine();
            switch (choice) {
                case "1":
                    clientMenu.menu();
                    break;
                case "2":
                    deviceMenu.menu();
                    break;
                case "3":
                    invoiceMenu.menuInvoices();
                    break;
                case "0":
                    InputOutput.printLine("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    InputOutput.printLine("Invalid input.");
                    break;
            }

        } while (!choice.equals("0"));
    }

}
