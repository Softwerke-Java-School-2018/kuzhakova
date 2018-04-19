package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelCheckPosition;
import ru.softwerke.catalog.entities.CheckPosition;

public class ControllerCheckPosition {
    ModelCheckPosition modelCheckPosition = new ModelCheckPosition();

    public boolean addCheckPosition(int idClient, int idDevice, int numberOf) {
        try {
            modelCheckPosition.getCheckPositions().add(new CheckPosition(idClient, idDevice, numberOf));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
