package ru.aston.finalproject.service.loader;

import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.util.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LoaderService<T> {
    private final Map<String, DataLoader<T>> loaders = new HashMap<>();

    public Stream<T> loadEntityList(String loaderKey, Integer size, AppRequest request) {
        if (!loaders.containsKey(loaderKey)) {
            throw new AppException(Message.WRONG_LOADER_KEY_X);
        }
        return loaders.get(loaderKey).loadEntityList(size, request);
    }

    public void addLoader(String loaderKey, DataLoader<T> dataLoader) {
        loaders.put(loaderKey, dataLoader);
    }
}
