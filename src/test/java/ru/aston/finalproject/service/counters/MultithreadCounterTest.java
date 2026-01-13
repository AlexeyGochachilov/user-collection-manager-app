package ru.aston.finalproject.service.counters;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.collection.CustomArrayList;

@Tag("counting")
public class MultithreadCounterTest {

    private static MultithreadCounter<Integer> counter;
    private static List<Integer> list;

    @BeforeAll
    static void init() {
        counter = new MultithreadCounter<>();

        list = new CustomArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                list.add(-1);
            }
            else {
                list.add(i);
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void givenMultithreadCounter_whenBadThreadCount_thenAppException(int threadCount) {
        Assertions.assertThrowsExactly(
                AppException.class,
                () -> counter.count(
                        list,
                        -1,
                        threadCount
                )
        );
    }

    @Test
    void givenMultithreadCounter_whenNullList_thenAppException() {
        Assertions.assertThrowsExactly(
                AppException.class,
                () -> counter.count(
                        null,
                        0,
                        1
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 2})
    void givenMultithreadCounter_whenDifferentTargets_thenCorrectCounting(int target) {
        Assertions.assertEquals(
                list.stream().filter((number) -> number == target).count(),
                counter.count(
                        list,
                        target,
                        4
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void givenMultithreadCounter_whenDifferentThreadCount_thenCorrectCounting(int threadCount) {
        Assertions.assertEquals(
                list.stream().filter((number) -> number == -1).count(),
                counter.count(
                        list,
                        -1,
                        threadCount
                )
        );
    }
}
