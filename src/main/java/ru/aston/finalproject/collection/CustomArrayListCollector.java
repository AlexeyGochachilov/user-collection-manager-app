package ru.aston.finalproject.collection;

import java.util.stream.Collector;

public class CustomArrayListCollector {

    public static <T> Collector<T, CustomArrayList<T>, CustomArrayList<T>> toCustomArrayList() {
        return Collector.of(
                CustomArrayList::new,
                CustomArrayList::add,
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
    }
}
