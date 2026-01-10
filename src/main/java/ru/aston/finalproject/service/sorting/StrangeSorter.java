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

        new MergeSorter().sort(evens, Comparator.comparing(getIntegerField));

        for (int i = 0; i < sequentialPositionsOfEvens.size(); i++) {
            list.set(
                    sequentialPositionsOfEvens.get(i),
                    evens.get(i)
            );
        }

        return list;
    }
}
