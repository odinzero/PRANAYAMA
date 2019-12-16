package com.pranayama.ui.list;

import java.util.*;
import javax.swing.*;


class GenericListModel<T> extends AbstractListModel {

    private static final long serialVersionUID = 1L;
    private List<T> data = Collections.synchronizedList(new ArrayList<T>());

    public GenericListModel() {
        super();
    }

    public final void setData(final List<T> newData) {
        data.clear();
        data.addAll(newData);
        fireContentsChanged(this, 0, data.size() - 1);
    }

    public final List<T> getData() {
        return new ArrayList<T>(data);
    }

    public final void add(final T value) {
        int changeIndex = data.size();
        data.add(value);
        fireIntervalAdded(this, changeIndex, changeIndex);
    }

    public final boolean remove(final T value) {
        boolean result = false;
        int index = data.indexOf(value);
        if (index >= 0) {
            result = data.remove(value);
            fireIntervalRemoved(this, index, index);
        }
        return result;
    }

    public final Object getElementAt(int index) {
        return data.get(index);
    }

    public final int getSize() {
        return data.size();
    }
}
