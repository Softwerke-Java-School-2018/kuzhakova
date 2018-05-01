package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.ClientController;
import ru.softwerke.catalog.view.io.IOUtils;
import ru.softwerke.catalog.view.io.InputOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientMenu extends MainMenu {
    private ClientController clientController = new ClientController();
    private LocalDate birthDate;
    private String fName, lName, enterBirthDate;
    public static ClientMenu instance;
    DateTimeFormatter formatter;

    private ClientMenu() {
    }

    public static ClientMenu getInstance() {
        if (instance == null) {
            synchronized (ClientMenu.class) {
                if (instance == null)
                    instance = new ClientMenu();
            }
        }
        return instance;
    }

    public final String MENU_CLIENT = "\nClients:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete client\n" +
            "4. Find client\n" +
            "5. Sort clients\n" +
            "0. Back";
    public final String MENU_SORT_CLIENTS = "\nSort clients:\n" +
            "1. By first name\n" +
            "2. By last name\n" +
            "3. By birth date\n" +
            "0. Back";

    @Override
    public void menu() {
        String choice;
        do {
            InputOutput.printLine(MENU_CLIENT);
            choice = InputOutput.readLine();
            switch (choice) {
                case "1":
                    menuPrintClients();
                    break;
                case "2":
                    menuAddClient();
                    break;
                case "3":
                    menuDeleteClient();
                    break;
                case "4":
                    menuFindClient();
                    break;
                case "5":
                    menuSortClients();
                    break;
                case "0":
                    break;
                default:
                    InputOutput.printLine("Invalid input.");
                    break;
            }

        } while (!choice.equals("0"));
    }

    public void menuPrintClients() {
        String[] clients = clientController.clientListToStringArray();
        for (String c : clients) {
            InputOutput.printLine(c);
        }
    }

    public void menuSortClients() {
        InputOutput.printLine(MENU_SORT_CLIENTS);
        int what = InputOutput.readInt();
        if (!IOUtils.isCorrectParameter(what, clientController.comparatorsCount())) return;
        InputOutput.printLine(MENU_HOW_SORT);
        int how = InputOutput.readInt();
        if (!IOUtils.isCorrectParameter(how, 2)) return;
        clientController.sort(what, how);
    }


    public void enterData() {
        birthDate = null;
        InputOutput.printLine("Enter first name:");
        fName = InputOutput.readLine();
        InputOutput.printLine("Enter last name:");
        lName = InputOutput.readLine();
        InputOutput.printLine("Enter date of birth (dd/mm/yyyy):");
        enterBirthDate = InputOutput.readLine();
        boolean isEmptyDate = enterBirthDate.equals("");
        if (!isEmptyDate) {
            while (!IOUtils.checkEnterDateWithRegExp(enterBirthDate)) {
                InputOutput.printLine("Wrong enter! Enter date:");
                enterBirthDate = InputOutput.readLine();
            }
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            birthDate = LocalDate.parse(enterBirthDate, formatter);
        } else birthDate = LocalDate.MAX;
    }

    public void menuAddClient() {
        enterData();
        if (clientController.addClient(fName, lName, birthDate))
            InputOutput.printLine("Client successfully added.");
        else InputOutput.printLine("Invalid input. The information was not completely entered.");
    }


    public void menuDeleteClient() {
        enterData();
        if (!clientController.deleteClient(fName, lName, birthDate)) {
            if (!clientController.printFoundClientList(fName, lName, birthDate)) {
                InputOutput.printLine("Removal did not happen.");
                return;
            }
        }
        InputOutput.printLine("Client successfully deleted.");
    }

    public void menuFindClient() {
        enterData();
        if (clientController.findClient(fName, lName, birthDate) == null &&
                clientController.findSimilarClients(fName, lName, birthDate) == false)
            InputOutput.printLine("No clients found for this query.");
    }
}