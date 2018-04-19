package ru.softwerke.catalog.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    ClientMenu clientMenu;
    DeviceMenu viewDevice;
    CheckMenu viewCheck;

    public final String MAIN_MENU = "Menu\n" +
            "1. Clients\n" +
            "2. Devices\n" +
            "3. Checks\n" +
            "0. Exit\n";
    public final String MENU_HOW_SORT = "Sort:\n" +
            "1. Ascending\n" +
            "2. Descending\n";

    public void menu() {
        clientMenu = ClientMenu.getInstance();
        viewDevice = DeviceMenu.getInstance();
        viewCheck = CheckMenu.getInstance();
        String choice;
        do {
            InputOutput.printLine(MAIN_MENU);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    clientMenu.menu();
                    break;
                case "2":
                    viewDevice.menu();
                    break;
                case "3":
                    viewCheck.menuChecks();
                    break;
                case "0":
                    InputOutput.printLine("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    InputOutput.printLine("Input error.");
                    break;
            }

        } while (!choice.equals("0"));
    }

    public boolean checkEnterDateWithRegExp(String dateString) {
        Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
        Matcher m = p.matcher(dateString);
        return m.matches();
    }
}