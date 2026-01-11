package ru.aston.finalproject.util;

import ru.aston.finalproject.app.AppException;

import static ru.aston.finalproject.util.Message.X_CANNOT_BE_EMPTY;

public class ConstantMethods {

    public static void checkedStringOnEmpty(String string, String field) {
        if (string == null || string.trim().isEmpty()) {
            throw new AppException(String.format(X_CANNOT_BE_EMPTY, field));
        }
    }
}