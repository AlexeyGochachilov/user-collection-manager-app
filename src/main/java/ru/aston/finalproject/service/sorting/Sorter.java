package ru.aston.finalproject.service.sorting;

import java.util.Comparator;
import java.util.List;

public abstract class Sorter {

    public abstract <ListItemT> List<ListItemT> sort(
            List<ListItemT> list,
            Comparator<ListItemT> comparator
    );

    public <ListItemT extends Comparable<ListItemT>> List<ListItemT> sort(
            List<ListItemT> list
    ) {
        return sort(list, Comparator.naturalOrder());
    }
}
