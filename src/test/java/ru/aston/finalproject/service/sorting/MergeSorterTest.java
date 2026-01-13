package ru.aston.finalproject.service.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.aston.finalproject.collection.CustomArrayList;

@Tag("sorting")
public class MergeSorterTest {

    private static Sorter sorter;
    private static List<Integer> integerList;
    private static List<Integer> integerListOrdered;
    private static List<Integer> integerListRandom;

    @BeforeAll
    static void init() {
        sorter = new MergeSorter();

        integerList = new CustomArrayList<>();
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0)
                integerList.add(i);
            else
                integerList.add(9);
        }

        integerListOrdered = new CustomArrayList<>();
        for (int i = 1; i <= 20; i++) {
            integerListOrdered.add(i);
        }

        integerListRandom = new CustomArrayList<>();
        Random generator = new Random();
        for (int i = 1; i <= 20; i++) {
            integerListRandom.add(generator.nextInt());
        }
    }

    static Stream<List<Integer>> integerListProvider(){
        return Stream.of(integerList, integerListOrdered, integerListRandom);
    }

    @ParameterizedTest
    @MethodSource("integerListProvider")
    void givenMergeSorter_whenComparableItem_thenCorrectSorting(List<Integer> list) {
        List<Integer> utilsSortList = new CustomArrayList<>(list);
        utilsSortList.sort(null);
        List<Integer> mergeSortList = new CustomArrayList<>(list);
        Assertions.assertEquals(
                utilsSortList,
                sorter.sort(mergeSortList)
        );
    }

    @ParameterizedTest
    @MethodSource("integerListProvider")
    void givenMergeSorter_whenNaturalComparator_thenCorrectSorting(List<Integer> list) {
        List<Integer> utilsSortList = new CustomArrayList<>(list);
        utilsSortList.sort(null);
        List<Integer> mergeSortList = new CustomArrayList<>(list);
        Assertions.assertEquals(
                utilsSortList,
                sorter.sort(
                        mergeSortList,
                        Comparator.naturalOrder()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("integerListProvider")
    void givenMergeSorter_whenInverseComparator_thenCorrectSorting(List<Integer> list) {
        Comparator<Integer> invertedComparator = Comparator.comparing(
                (num) -> -num
        );
        List<Integer> utilsSortList = new CustomArrayList<>(list);
        utilsSortList.sort(invertedComparator);
        List<Integer> mergeSortList = new CustomArrayList<>(list);
        Assertions.assertEquals(
                utilsSortList,
                sorter.sort(
                        mergeSortList,
                        invertedComparator
                )
        );
    }

    @Test
    void givenMergeSorter_whenNull_thenReturnEmptyList() {
        Assertions.assertTrue(
                sorter.sort(null).isEmpty()
        );
    }
}
