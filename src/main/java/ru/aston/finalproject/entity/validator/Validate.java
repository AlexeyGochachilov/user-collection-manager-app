package ru.aston.finalproject.entity.validator;

import ru.aston.finalproject.environment.AppException;

public interface Validate<T> {

    void validate(Object... obj) throws AppException;
}
