package ru.aston.finalproject.service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSorter extends Sorter {
    public <ListItemT> List<ListItemT> sort(
            List<ListItemT> list,
            Comparator<ListItemT> comparator
    ) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<ListItemT> left = new ArrayList<>(list.subList(0, mid));
        List<ListItemT> right = new ArrayList<>(list.subList(mid, list.size()));

        sort(left, comparator);
        sort(right, comparator);

        merge(list, left, right, comparator);

        return list;
    }

    private <ListItemT> void merge(
            List<ListItemT> result,
            List<ListItemT> left,
            List<ListItemT> right,
            Comparator<ListItemT> comparator
    ) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }
}
