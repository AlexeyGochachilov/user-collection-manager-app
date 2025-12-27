package ru.aston.finalproject.validators;

@FunctionalInterface
public interface Validator {

    void validateEntity(String username, String email, int age);
}
