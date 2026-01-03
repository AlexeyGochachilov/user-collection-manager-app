package ru.aston.finalproject.service;

import java.util.List;

public interface DataLoader<T> {

    List<T> loadEntityList(Integer size);
}
