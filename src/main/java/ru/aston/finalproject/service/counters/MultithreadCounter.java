package ru.aston.finalproject.service.counters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadCounter {
    public <ListItemT> int count(
           List<ListItemT> list,
           ListItemT target,
           int threadCount
    ) throws ExecutionException, InterruptedException {
        if (threadCount < 1) {
            throw new IllegalArgumentException(
                   "Expected thread count to be a positive integer."
            );
        }

        int step = list.size() / threadCount;
        if (threadCount == 1 || step < 1) {
           return count(list, target);
        }

        int returnValue;
        // ExecutorService не является AutoCloseable до 19й версии java.
        ExecutorService tasks = Executors.newFixedThreadPool(threadCount);
        try {
            List<Future<Integer>> results = new ArrayList<>();
            for (int multiplier = 0; multiplier < threadCount - 1; multiplier++) {
                int finalMultiplier = multiplier;
                results.add(tasks.submit(
                        () -> count(
                                list.subList(step * finalMultiplier, step * (finalMultiplier + 1)),
                                target
                        )
                ));
            }
            results.add(tasks.submit(
                    () -> count(
                            list.subList(step * (threadCount - 1), list.size()),
                            target
                    )
            ));

            returnValue = 0;
            for (Future<Integer> value : results) {
                returnValue += value.get();
            }
        }
        finally {
            tasks.shutdown();
        }
        return returnValue;
    }

    public <ListItemT> int count(
           List<ListItemT> list,
           ListItemT target
    ) {
        int count = 0;
        for (ListItemT item : list) {
            if (item.equals(target)) {
                count++;
            }
        }
        return count;
    }
}
