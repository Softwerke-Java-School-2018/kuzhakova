package ru.softwerke.catalog;

import ru.softwerke.catalog.view.View;

public class Main {
    public static void main(String[] args) {
        System.out.println("The catalog of salon");
        View view = new View();
        view.menu();
    }
}
