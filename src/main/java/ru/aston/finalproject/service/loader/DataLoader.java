package ru.aston.finalproject.service.loader;

import java.util.List;

public interface DataLoader<T> {

    List<T> loadEntityList(Integer size);
}
