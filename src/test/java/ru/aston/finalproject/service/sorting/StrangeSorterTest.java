package ru.aston.finalproject.service.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.aston.finalproject.collection.CustomArrayList;

public class StrangeSorterTest {

    private static StrangeSorter sorter;
    private static List<Integer> integerList;

    @BeforeAll
    static void init() {
        sorter = new StrangeSorter();
        integerList = new CustomArrayList<>(Arrays.asList(
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        ));
    }

    record FunctionAndResult(
            Function<Integer, Integer> function,
            List<Integer> result
    ) {}

    static Stream<FunctionAndResult> FunctionAndResultProvider() {
        return Stream.of(
                new FunctionAndResult(
                        (integer) -> integer,
                        new CustomArrayList<>(Arrays.asList(
                                2, 9, 4, 7, 6, 5, 8, 3, 10, 1
                        ))
                ),
                new FunctionAndResult(
                        (integer) -> -integer,
                        new CustomArrayList<>(Arrays.asList(
                                10, 9, 8, 7, 6, 5, 4, 3, 2, 1
                        ))
                ),
                new FunctionAndResult(
                        (integer) -> integer + 1,
                        new CustomArrayList<>(Arrays.asList(
                                10, 1, 8, 3, 6, 5, 4, 7, 2, 9
                        ))
                )
        );

    }

    @ParameterizedTest
    @MethodSource("FunctionAndResultProvider")
    void givenStrangeSorter_whenFunction_thenCorrectSorting(FunctionAndResult functionAndResult) {
        Assertions.assertEquals(
                functionAndResult.result,
                sorter.sort(
                        integerList,
                        functionAndResult.function
                )
        );
    }

    @Test
    void givenStrangeSorter_whenNull_thenReturnNull() {
        Assertions.assertNull(
                sorter.sort(
                        null,
                        (integer) -> 0
                )
        );
    }
}
