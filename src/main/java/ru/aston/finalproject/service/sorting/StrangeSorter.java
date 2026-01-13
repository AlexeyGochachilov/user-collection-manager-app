package ru.aston.finalproject.service.sorting;

import lombok.AllArgsConstructor;
import ru.aston.finalproject.collection.CustomArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@AllArgsConstructor
public class StrangeSorter {
    private Sorter mergeSorter;

    public <ListItemT> List<ListItemT> sort(List<ListItemT> list,
                                            Function<ListItemT, Integer> getIntegerField) {
        if (Objects.isNull(list)) {
            return new CustomArrayList<>();
        }

        List<ListItemT> evens = new CustomArrayList<>();
        List<Integer> sequentialPositionsOfEvens = new CustomArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            ListItemT reference = list.get(i);

            if (getIntegerField.apply(reference) % 2 == 0) {
                evens.add(reference);
                sequentialPositionsOfEvens.add(i);
            }
        }

        mergeSorter.sort(evens, Comparator.comparing(getIntegerField));

        for (int i = 0; i < sequentialPositionsOfEvens.size(); i++) {
            list.set(sequentialPositionsOfEvens.get(i), evens.get(i));
        }

        return list;
    }
}
