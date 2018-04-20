package ru.softwerke.catalog.model;

import ru.softwerke.catalog.entities.CheckPosition;

import java.util.ArrayList;
import java.util.List;

public class ModelCheckPosition {
    private List<CheckPosition> checkPositions = new ArrayList<CheckPosition>();

    public List<CheckPosition> getCheckPositions() {
        return checkPositions;
    }

}
