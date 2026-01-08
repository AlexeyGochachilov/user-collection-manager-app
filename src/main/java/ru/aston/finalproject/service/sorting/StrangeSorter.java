package ru.aston.finalproject.service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class StrangeSorter {

    public <ListItemT> List<ListItemT> sort(
            List<ListItemT> list,
            Function<ListItemT, Integer> getIntegerField
    ) {
        List<ListItemT> evens = new ArrayList<>();
        List<Integer> sequentialPositionsOfEvens = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            ListItemT reference = list.get(i);
            if (
                    getIntegerField.apply(reference) % 2 == 0
            ) {
                evens.add(reference);
                sequentialPositionsOfEvens.add(i);
            }
        }

        mergeSort(list, Comparator.comparing(getIntegerField));

        for (int i = 0; i < sequentialPositionsOfEvens.size(); i++) {
            list.set(
                    sequentialPositionsOfEvens.get(i),
                    evens.get(i)
            );
        }

        return list;
    }

    // TODO: Дублирую слегка измененный код Виталия, это против DRY
    private <ListItemT> void mergeSort(
            List<ListItemT> list,
            Comparator<ListItemT> comparator
    ) {
        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        List<ListItemT> left = new ArrayList<>(list.subList(0, mid));
        List<ListItemT> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        merge(list, left, right, comparator);
    }

    // TODO: Дублирую слегка измененный код Виталия, это против DRY
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
