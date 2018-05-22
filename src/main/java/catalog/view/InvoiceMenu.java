package catalog.view;

import catalog.controller.InvoiceController;
import catalog.model.entities.Invoice;
import catalog.view.io.IOUtils;
import catalog.view.io.InputOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceMenu extends MainMenu {
    InvoiceController invoiceController = new InvoiceController();
    private int idClient;
    int id;
    int[] idDevices;
    int[] countDevices;
    private LocalDate dateSale;
    DateTimeFormatter formatter;

    public static String MENU_INVOICE = "Invoices:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete invoice\n" +
            "4. Find invoice\n" +
            "5. Sort invoice\n" +
            "0. Back";

    public final String MENU_SORT_INVOICES = "\nSort invoices:\n" +
            Invoice.toStringPropertiesList() +
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
                    menuAddInvoice();
                    break;
                case "3":
                    menuDeleteInvoice();
                    break;
                case "4":
                    menuFindInvoice();
                    break;
                case "5":
                    menuSortInvoice();
                    break;
                case "0":
                    break;
                default:
                    InputOutput.printLine("Invalid input.");
                    break;
            }

        } while (!choice.equals("0"));
    }

    public void enterData() {
        InputOutput.printLine("Enter id of client:");
        idClient = InputOutput.readInt();
        InputOutput.printLine("Enter date of sale (dd/mm/yyyy):");
        String enterSaleDate = InputOutput.readLine();
        if (!"".equals(enterSaleDate)) {
            while (!IOUtils.checkEnterDateWithRegExp(enterSaleDate)) {
                InputOutput.printLine("Wrong enter! Enter date:");
                enterSaleDate = InputOutput.readLine();
            }
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateSale = LocalDate.parse(enterSaleDate, formatter);
        }
        InputOutput.printLine("Enter enter the number of different devices:");
        int numderOfDevices = InputOutput.readInt();
        idDevices = new int[numderOfDevices];
        countDevices = new int[numderOfDevices];
        for (int i = 0; i < numderOfDevices; i++) {
            InputOutput.printLine("Enter ID of device:"); //TODO: same devices
            idDevices[i] = InputOutput.readInt();
            InputOutput.printLine("Enter count of device:");
            countDevices[i] = InputOutput.readInt();
        }
    }

    public void menuPrintInvoices() {
        invoiceController.printList();
    }

    public void menuAddInvoice() {
        enterData();
        if (invoiceController.addInvoice(idClient, idDevices, countDevices, dateSale)) {
            InputOutput.printLine("Invoice successfully added.");
        } else InputOutput.printLine("Invalid input. The information was not completely entered.");
    }

    public void menuDeleteInvoice() {
        InputOutput.printLine("Enter ID of invoice:");
        id = InputOutput.readInt();
        if (invoiceController.deleteByID(id)) {
            InputOutput.printLine("Invoice successfully deleted.");
            return;
        }
        InputOutput.printLine("Removal did not happen.");
    }

    public void menuFindInvoice() {
        InputOutput.printLine("Enter ID of invoice:"); //TODO: not only ID
        id = InputOutput.readInt();
        if (!invoiceController.findByID(id)) {
            InputOutput.printLine("No invoices found for this query.");
        }
    }

    public void menuSortInvoice() {
        try {
            InputOutput.printLine(MENU_SORT_INVOICES);
            int property = InputOutput.readInt();
            if (!IOUtils.isCorrectParameter(property, invoiceController.comparatorsCount())) return;
            String propertyInArray = Invoice.getPropertyInArray(property - 1);
            InputOutput.printLine(MENU_HOW_SORT);
            int sortingParameter = InputOutput.readInt();
            if (!IOUtils.isCorrectParameter(sortingParameter, sortingParameters.length)) return;
            invoiceController.sort(propertyInArray, sortingParameter);
        } catch (IllegalArgumentException e) {
            InputOutput.printLine(e.getMessage());
        }
    }
}
