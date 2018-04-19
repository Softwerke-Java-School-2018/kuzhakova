package ru.softwerke.catalog;

import ru.softwerke.catalog.view.InputOutput;
import ru.softwerke.catalog.view.MainMenu;

public class Main {
    public static void main(String[] args) {
        InputOutput.printLine("The catalog of salon");
        new MainMenu().menu();
    }
}
