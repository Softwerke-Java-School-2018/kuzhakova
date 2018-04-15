package ru.softwerke.catalog.View;

import ru.softwerke.catalog.Controller.ControllerCheck;
import java.util.Scanner;

public class ViewCheck extends View {
    ControllerCheck controllerCheck = new ControllerCheck();
    Scanner scanner = new Scanner(System.in);
    public static String MENU_CHECK = "Checks:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete check\n" +
            "4. Find check\n" +
            "5. Sort check\n" +
            "0. Back";

    public void menuChecks() {
        System.out.println(MENU_CHECK);
        String choice = scanner.nextLine();
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
