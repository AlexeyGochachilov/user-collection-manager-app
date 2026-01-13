package ru.aston.finalproject.service.sorting;

import lombok.NonNull;
import ru.aston.finalproject.collection.CustomArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MergeSorter extends Sorter {

    public <T> List<T> sort(List<T> list, @NonNull Comparator<T> comparator) {

        if (Objects.isNull(list)) {
            return new CustomArrayList<>();
        }

        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<T> left = new CustomArrayList<>(list.subList(0, mid));
        List<T> right = new CustomArrayList<>(list.subList(mid, list.size()));

        sort(left, comparator);
        sort(right, comparator);

        merge(list, left, right, comparator);

        return list;
    }

    private <T> void merge(List<T> result, List<T> left, List<T> right,
                           Comparator<T> comparator) {
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
