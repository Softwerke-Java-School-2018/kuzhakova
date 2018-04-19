package ru.softwerke.catalog.model;

import ru.softwerke.catalog.entities.Check;

import java.util.ArrayList;
import java.util.List;

public class ModelCheck {
    private List<Check> checkList = new ArrayList<Check>();

    public List<Check> getCheckList() {
        return checkList;
    }
}
