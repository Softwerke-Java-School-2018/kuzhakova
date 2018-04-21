package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.ControllerInvoice;

public class InvoiceMenu extends MainMenu {
    ControllerInvoice controllerInvoice = new ControllerInvoice();

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
        InputOutput.printLine(MENU_INVOICE);
        String choice = InputOutput.readLine();
        do {
            switch (choice) {
                case "1":
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
}
