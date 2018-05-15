package catalog.view;

import catalog.controller.DeviceController;
import catalog.model.entities.Device;
import catalog.model.enums.*;
import catalog.view.io.IOUtils;
import catalog.view.io.InputOutput;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DeviceMenu extends MainMenu {
    public static DeviceMenu instance;
    private DeviceController deviceController = new DeviceController();
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
    public final String MENU_SORT_DEVICES = "\nSort devices by:\n" +
            Device.toStringPropertiesList() +
            "0. Back";

    private DeviceMenu() {
    }

    public static DeviceMenu getInstance() {
        if (instance == null) {
            synchronized (DeviceMenu.class) {
                if (instance == null)
                    instance = new DeviceMenu();
            }
        }
        return instance;
    }

    @Override
    public void menu() {
        String choice;
        do {
            InputOutput.printLine(MENU_DEVICE);
            choice = InputOutput.readLine();
            switch (choice) {
                case "1":
                    menuPrintDevices();
                    break;
                case "2":
                    menuAddDevice();
                    break;
                case "3":
                    menuDeleteDevice();
                    break;
                case "4":
                    menuFindDevice();
                    break;
                case "5":
                    menuSortDevices();
                    break;
                case "0":
                    break;
                default:
                    InputOutput.printLine("Invalid input.");
                    break;
            }

        } while (!choice.equals("0"));
    }

    private void menuSortDevices() {
        try {
            InputOutput.printLine(MENU_SORT_DEVICES);
            int property = InputOutput.readInt();
            if (!IOUtils.isCorrectParameter(property, deviceController.comparatorsCount())) return;
            String propertyInArray = Device.getPropertyInArray(property - 1);
            InputOutput.printLine(MENU_HOW_SORT);
            int sortingParameter = InputOutput.readInt();
            if (!IOUtils.isCorrectParameter(sortingParameter, sortingParameters.length)) return;
            deviceController.sort(propertyInArray, sortingParameter);
        } catch (IllegalArgumentException e) {
            InputOutput.printLine(e.getMessage());
        }
    }

    public void enterDataOfDevice() {
        InputOutput.printLine("Enter manufacturer of device:");
        manufacturer = IOUtils.lookupEnum(Manufacturer.class);

        InputOutput.printLine("Enter color of device:");
        color = IOUtils.lookupEnum(Color.class);

        InputOutput.printLine("Enter date of release (dd/mm/yyyy):");
        String enterReleaseDate = InputOutput.readLine();
        while (!IOUtils.checkEnterDateWithRegExp(enterReleaseDate)) {
            InputOutput.printLine("Wrong enter! Enter date:");
            enterReleaseDate = InputOutput.readLine();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        releaseDate = LocalDate.parse(enterReleaseDate, formatter);

        InputOutput.printLine("Enter type of device (laptop, phone, player, tablet):");
        deviceType = IOUtils.lookupEnum(DeviceType.class);

        InputOutput.printLine("Enter price of device:");
        price = InputOutput.readBigDecimal();

        InputOutput.printLine("Enter model of device:");
        model = InputOutput.readLine();
    }

    public void enterDataToFind() {
    }


    public void menuPrintDevices() {
        InputOutput.printLine("type | date of release | price | color| manufacturer, model ");
        InputOutput.printLine("-----------------------------------------------------------------------");
        String[] devices = deviceController.deviceListToStringArray();
        for (String c : devices) {
            InputOutput.printLine(c);
        }
    }

    public void menuAddDevice() {
        enterDataOfDevice();
        if (deviceController.addDevice(model, manufacturer, color, releaseDate, deviceType, price)) {
            InputOutput.printLine("Device successfully added.");
        } else InputOutput.printLine("Could not add device.");
    }

    public void menuDeleteDevice() {
        InputOutput.printLine("Enter ID of device:");
        int id = InputOutput.readInt();
        if (deviceController.deleteByID(id)) {
            InputOutput.printLine("Device successfully deleted.");
            return;
        }
        InputOutput.printLine("Removal did not happen.");
    }

    public void menuFindDevice() {
        enterDataOfDevice();
        if (Objects.isNull(deviceController.findDevice(model, manufacturer, color, releaseDate, deviceType, price)) &&
                deviceController.findSimilarDevices(model, manufacturer, color, releaseDate, deviceType, price) == false)
            InputOutput.printLine("No devices found for this query.");
    }

}
