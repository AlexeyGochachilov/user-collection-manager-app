package ru.aston.finalproject.validators;

@FunctionalInterface
public interface Validator<T> {

    void validate(String fieldOne, String fieldTwo, int fieldInt);
}
