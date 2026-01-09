package ru.aston.finalproject.service.loader;

import ru.aston.finalproject.app.AppRequest;

import java.util.List;

public interface DataLoader<T> {

    List<T> loadEntityList(Integer size, AppRequest request);
}
