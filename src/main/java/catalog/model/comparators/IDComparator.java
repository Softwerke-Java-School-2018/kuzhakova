package catalog.model.comparators;

import catalog.model.entities.DataItem;

import java.util.Comparator;

public class IDComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        DataItem dataItem1 = (DataItem)o1;
        DataItem dataItem2 = (DataItem)o2;
        return dataItem1.compareTo(dataItem2);
    }
}
