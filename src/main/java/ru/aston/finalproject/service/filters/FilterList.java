package ru.aston.finalproject.service.filters;

import java.util.List;

public interface FilterList<T> {

    public List<T> filter(List<T> list, Double... value);
}
