package ru.aston.finalproject.validators;

@FunctionalInterface
public interface Validator {

    void validateEntity(String fieldOne, String fieldTwo, int fieldInt);
}
