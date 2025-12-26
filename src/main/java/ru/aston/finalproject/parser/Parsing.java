package ru.aston.finalproject.parser;

public interface Parsing<T> {

    T parse(String data, String delimiter);
}
