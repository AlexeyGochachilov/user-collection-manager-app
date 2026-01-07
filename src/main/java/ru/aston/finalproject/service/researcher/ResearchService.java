package ru.aston.finalproject.service.researcher;

import lombok.NonNull;
import ru.aston.finalproject.util.Message;

import java.util.List;

public class ResearchService<T> {

    public void countEntityOccurrences(@NonNull List<T> entityList, @NonNull T entity) {
        // TODO сделать поиск многопоточным
        long result = entityList.stream().filter(entity::equals).count();
        System.out.printf(Message.EXCEPTION_X_X_FOUND_IN_LIST, result, entity.getClass().getSimpleName().toLowerCase());
    }
}
