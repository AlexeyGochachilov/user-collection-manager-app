package ru.aston.finalproject.staticTools;

import ru.aston.finalproject.appEnviroment.AppException;

import static ru.aston.finalproject.staticTools.Message.X_CANNOT_BE_EMPTY;

public class ConstantMethods {

    public static void checkedStringOnEmpty(String string, String field) {
        if (string == null || string.trim().isEmpty()) {
            throw new AppException(String.format(X_CANNOT_BE_EMPTY, field));
        }
    }
}