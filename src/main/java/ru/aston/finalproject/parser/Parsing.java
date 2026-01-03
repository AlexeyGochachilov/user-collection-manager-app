package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Entity;

public interface Parsing<T> {

    String parseToString(T t);

    T parse(String data);

    T parse(String data, String delimiter);

}
