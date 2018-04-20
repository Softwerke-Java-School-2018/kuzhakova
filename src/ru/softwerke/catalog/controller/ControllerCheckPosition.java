package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelCheckPosition;
import ru.softwerke.catalog.entities.CheckPosition;

public class ControllerCheckPosition {
    ModelCheckPosition modelCheckPosition = new ModelCheckPosition();

    public boolean addCheckPosition(int idClient, int idDevice, int count) {
        try {
            CheckPosition checkPosition = CheckPosition.newCheckPositionBuilder()
                    .setIdDevice(idDevice)
                    .setIdClient(idClient)
                    .setCount(count)
                    .build();
            modelCheckPosition.getCheckPositions().add(checkPosition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
