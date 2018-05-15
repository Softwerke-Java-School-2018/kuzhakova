package catalog;

import catalog.model.DataGenerator;
import catalog.view.io.InputOutput;
import catalog.view.MainMenu;

public class AppCatalog {
    public static void main(String[] args) {
        new DataGenerator().generateData();
        InputOutput.printLine("The catalog of salon");
        new MainMenu().menu();
    }
}