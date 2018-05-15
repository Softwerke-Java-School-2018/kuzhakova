package catalog.model.entities;

import java.util.Objects;

public abstract class DataItem implements Comparable<DataItem> {
    protected int id;

    protected DataItem() {
    }

    public int getID() {
        return id;
    }

    @Override
    public int compareTo(DataItem o) {
        return ((Integer) this.getID()).compareTo(o.getID());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataItem dataItem = (DataItem) o;
        return id == dataItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
