package ru.softwerke.catalog;

import ru.softwerke.catalog.model.DataGenerator;
import ru.softwerke.catalog.view.io.InputOutput;
import ru.softwerke.catalog.view.MainMenu;

public class AppCatalog {
    public static void main(String[] args) {
        new DataGenerator().generateData();
        InputOutput.printLine("The catalog of salon");
        new MainMenu().menu();
    }
}
