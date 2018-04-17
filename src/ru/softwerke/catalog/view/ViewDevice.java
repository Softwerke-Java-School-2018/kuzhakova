package ru.softwerke.catalog.view;

import ru.softwerke.catalog.controller.ControllerDevice;
import ru.softwerke.catalog.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ViewDevice extends View {
    public static ViewDevice instance;
    Scanner scanner = new Scanner(System.in);
    private ControllerDevice controllerDevice = new ControllerDevice();
    private Manufacturer manufacturer;
    private Color color;
    private LocalDate releaseDate;
    private DeviceType deviceType;
    private BigDecimal price;
    private String model;
    public final String MENU_DEVICE = "Devices:\n" +
            "1. Print list\n" +
            "2. Add to list\n" +
            "3. Delete device\n" +
            "4. Find device\n" +
            "5. Sort device\n" +
            "0. Back";

    private ViewDevice() {
    }

    public static ViewDevice getInstance() {
        if (instance == null)
            synchronized (ViewDevice.class) {
                if (instance == null)
                    instance = new ViewDevice();
            }
        return instance;
    }

    @Override
    public void menu() {
        String choice;
        do {
            System.out.println(MENU_DEVICE);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    //controllerDevice.printDeviceList();
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

    public void toEnterDataOfDevice() {
        System.out.println("Enter manufacturer of device:");
        String manufacturerString = scanner.nextLine();
        manufacturer = Manufacturer.valueOf(manufacturerString.toUpperCase());

        System.out.println("Enter color of device:");
        String colorString = scanner.nextLine();
        color = Color.valueOf(colorString.toUpperCase());

        System.out.println("Enter date of release (dd/mm/yyyy):");
        String enterReleaseDate = scanner.nextLine();
        while (!checkEnterDateWithRegExp(enterReleaseDate)) {
            System.out.println("Wrong enter! Enter date:");
            enterReleaseDate = scanner.nextLine();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        releaseDate = LocalDate.parse(enterReleaseDate, formatter);

        System.out.println("Enter type of device:");
        String typeString = scanner.nextLine();
        deviceType = DeviceType.valueOf(typeString.toUpperCase());

        System.out.println("Enter price of device:");
        price = new BigDecimal(scanner.nextLine());

        System.out.println("Enter model of device:");
        model = scanner.nextLine().toUpperCase();
    }

    public Boolean menuAddDevice() {
        toEnterDataOfDevice();
        return controllerDevice.addDevice(model, manufacturer, color, releaseDate, deviceType, price);
    }

}
