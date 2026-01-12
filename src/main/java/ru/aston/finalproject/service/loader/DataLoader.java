package ru.aston.finalproject.service.loader;

import ru.aston.finalproject.app.AppRequest;

import java.util.stream.Stream;

public interface DataLoader<T> {

    Stream<T> loadEntityList(Integer size, AppRequest request);
}
