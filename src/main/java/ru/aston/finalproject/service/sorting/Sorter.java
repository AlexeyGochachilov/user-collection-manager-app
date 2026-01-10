package ru.aston.finalproject.service.sorting;

import java.util.Comparator;
import java.util.List;

public abstract class Sorter {

    public abstract <T> List<T> sort(
            List<T> list,
            Comparator<T> comparator
    );

    public <T extends Comparable<T>> List<T> sort(
            List<T> list
    ) {
        return sort(list, Comparator.naturalOrder());
    }
}
