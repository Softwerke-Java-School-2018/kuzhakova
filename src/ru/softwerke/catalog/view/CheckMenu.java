package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.ControllerCheck;

public class CheckMenu extends MainMenu {
    ControllerCheck controllerCheck = new ControllerCheck();

    public static String MENU_CHECK = "Checks:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete check\n" +
            "4. Find check\n" +
            "5. Sort check\n" +
            "0. Back";
    public static CheckMenu instance;

    private CheckMenu() {
    }

    public static CheckMenu getInstance() {
        if (instance == null)
            synchronized (CheckMenu.class) {
                if (instance == null)
                    instance = new CheckMenu();
            }
        return instance;
    }

    public void menuChecks() {
        InputOutput.printLine(MENU_CHECK);
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
