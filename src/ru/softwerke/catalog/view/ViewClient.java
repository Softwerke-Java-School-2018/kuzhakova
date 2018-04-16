package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.ControllerClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ViewClient extends View {
    Scanner scanner = new Scanner(System.in);
    private ControllerClient controllerClient = new ControllerClient();
    private LocalDate birthDate;
    private String fName, lName, enterBirthDate;

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
            System.out.println(MENU_CLIENT);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    controllerClient.printClientList(System.out::println);
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
                    System.out.println("Invalid input.");
                    break;
            }

        } while (!choice.equals("0"));
    }

    public void menuSortClients() {
        Scanner scanInt = new Scanner(System.in);
        System.out.println(MENU_SORT_CLIENTS);
        int what = scanInt.nextInt();
        System.out.println(MENU_HOW_SORT);
        int how = scanInt.nextInt();
        controllerClient.sort(what, how);
    }

    public void enterData() {
        birthDate = null;
        System.out.println("Enter first name:");
        fName = scanner.nextLine();
        System.out.println("Enter last name:");
        lName = scanner.nextLine();
        System.out.println("Enter date of birth (dd/mm/yyyy):");
        enterBirthDate = scanner.nextLine();
        Boolean isEmptyDate = enterBirthDate.equals("");
        if (!isEmptyDate) {
            while (!checkEnterDateWithRegExp(enterBirthDate)) {
                System.out.println("Wrong enter! Enter date:");
                enterBirthDate = scanner.nextLine();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            birthDate = LocalDate.parse(enterBirthDate, formatter);
        }
    }

    public void menuAddClient() {
        enterData();
        if (controllerClient.addClient(fName, lName, birthDate))
            System.out.println("Client successfully added.");
        else System.out.println("Invalid input. The information was not completely entered.");
    }


    public void menuDeleteClient() {
        enterData();
        if (!controllerClient.deleteClient(fName, lName, birthDate)) {
            if (!controllerClient.printFoundClientList(fName, lName, birthDate)) {
                System.out.println("Removal did not happen.");
                return;
            }
        }
        System.out.println("Client successfully deleted.");
    }

    public void menuFindClient() {
        enterData();
        if (controllerClient.findClient(fName, lName, birthDate) == null &&
                controllerClient.findSimilarClients(fName, lName, birthDate) == false)
            System.out.println("No clients found for this query.");
    }
}
