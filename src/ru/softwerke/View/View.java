package ru.softwerke.View;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.softwerke.Controller.Controller;
import ru.softwerke.enums.*;

public class View {
    Controller controller = new Controller();
    private String strMainMenu = "Menu\n" +
            "1. Clients\n" +
            "2. Devices\n" +
            "3. Checks\n" +
            "0. Exit\n";
    private String strMenuClient = "Clients:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete client\n" +
            "4. Find client\n" +
            "5. Sort clients\n" +
            "0. Back";
    private String strMenuDevice = "Devices:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete device\n" +
            "4. Find device\n" +
            "5. Sort device\n" +
            "0. Back";
    private String strMenuCheck = "Checks:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete check\n" +
            "4. Find check\n" +
            "5. Sort check\n" +
            "0. Back";

    public void menu() {
        try {
            String choice;
            do {
                System.out.println(strMainMenu);
                choice = readLine();
                switch (choice) {
                    case "1":
                        menuClients();
                        break;
                    case "2":
                        menuDevices();
                        break;
                    case "3":
                        menuChecks();
                        break;
                    case "0":
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Input error.");
                        break;
                }

            } while (!choice.equals("0"));

        } catch (Exception e) {

        }
    }

    public void menuClients() {
        String choice;
        do {
            System.out.println(strMenuClient);
            choice = readLine();
            switch (choice) {
                case "1":
                    controller.printClientList();
                    break;
                case "2":
                    if (menuAddClient()) System.out.println("Client successfully added.");
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

    public void menuDevices() {
        String choice;
        do {
            System.out.println(strMenuDevice);
            choice = readLine();
            switch (choice) {
                case "1":
                    controller.printDeviceList();
                    break;
                case "2":
                    if (menuAddDevice()) System.out.println("Device successfully added.");
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

    public void menuChecks() {
        System.out.println(strMenuCheck);
        String choice = readLine();
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

    public Boolean menuAddDevice() {
        try {
            System.out.println("Enter manufacturer of device:");
            String manufacturerString = readLine();
            Manufacturer manufacturer = Manufacturer.getManufacturerByString(manufacturerString);

            System.out.println("Enter color of device:");
            String colorString = readLine();
            Color color = Color.getColorByString(colorString);

            System.out.println("Enter date of release (dd/mm/yyyy):");
            String enterReleaseDate = readLine();
            while (!checkWithRegExp(enterReleaseDate)){
                System.out.println("Wrong enter! Enter date:");
                enterReleaseDate = readLine();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate releaseDate = LocalDate.parse(enterReleaseDate, formatter);

            System.out.println("Enter type of device:");
            String typeString = readLine();
            DeviceType deviceType = DeviceType.getDeviceTypeByString(typeString);

            System.out.println("Enter price of device:");
            BigDecimal price = new BigDecimal(readLine());

            System.out.println("Enter model of device:");
            String model = readLine().toUpperCase();

            return controller.addDevice(model, manufacturer, color, releaseDate, deviceType, price);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean checkWithRegExp(String userNameString){
        Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }

    public Boolean menuAddClient() {
        try {
            System.out.println("Enter first name:");
            String fName = readLine();

            System.out.println("Enter last name:");
            String lName = readLine();

            System.out.println("Enter date of birth (dd/mm/yyyy):");
            String enterBirthDate = readLine();
            while (!checkWithRegExp(enterBirthDate)){
                System.out.println("Wrong enter! Enter date:");
                enterBirthDate = readLine();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(enterBirthDate, formatter);

            return controller.addClient(fName, lName, birthDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        return in;
    }

}
