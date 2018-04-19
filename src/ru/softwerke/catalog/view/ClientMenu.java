package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.ControllerClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientMenu extends MainMenu {
    private ControllerClient controllerClient = new ControllerClient();
    private LocalDate birthDate;
    private String fName, lName, enterBirthDate;
    public static ClientMenu instance;
    DateTimeFormatter formatter;

    private ClientMenu() {
    }

    public static ClientMenu getInstance() {
        if (instance == null)
            synchronized (ClientMenu.class) {
                if (instance == null)
                    instance = new ClientMenu();
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
            choice = scanner.nextLine();
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

    public void menuPrintClients(){
        String[] clients = controllerClient.clientListToStringArray();
        for (String c: clients){
            InputOutput.printLine(c);
        }
    }

    public void menuSortClients() {
        InputOutput.printLine(MENU_SORT_CLIENTS);
        int what = InputOutput.readInt();
        InputOutput.printLine(MENU_HOW_SORT);
        int how = InputOutput.readInt();
        controllerClient.sort(what, how);
    }

    public void enterData() {
        birthDate = null;
        InputOutput.printLine("Enter first name:");
        fName = scanner.nextLine();
        InputOutput.printLine("Enter last name:");
        lName = scanner.nextLine();
        InputOutput.printLine("Enter date of birth (dd/mm/yyyy):");
        enterBirthDate = scanner.nextLine();
        Boolean isEmptyDate = enterBirthDate.equals("");
        if (!isEmptyDate) {
            while (!checkEnterDateWithRegExp(enterBirthDate)) {
                InputOutput.printLine("Wrong enter! Enter date:");
                enterBirthDate = scanner.nextLine();
            }
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            birthDate = LocalDate.parse(enterBirthDate, formatter);
        }
        else birthDate = LocalDate.MAX;
    }

    public void menuAddClient() {
        enterData();
        if (controllerClient.addClient(fName, lName, birthDate))
            InputOutput.printLine("Client successfully added.");
        else InputOutput.printLine("Invalid input. The information was not completely entered.");
    }


    public void menuDeleteClient() {
        enterData();
        if (!controllerClient.deleteClient(fName, lName, birthDate)) {
            if (!controllerClient.printFoundClientList(fName, lName, birthDate)) {
                InputOutput.printLine("Removal did not happen.");
                return;
            }
        }
        InputOutput.printLine("Client successfully deleted.");
    }

    public void menuFindClient() {
        enterData();
        if (controllerClient.findClient(fName, lName, birthDate) == null &&
                controllerClient.findSimilarClients(fName, lName, birthDate) == false)
            InputOutput.printLine("No clients found for this query.");
    }
}
