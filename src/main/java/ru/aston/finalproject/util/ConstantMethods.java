package ru.aston.finalproject.util;

import ru.aston.finalproject.app.AppException;

import static ru.aston.finalproject.util.ConstantFields.DELIMITER;
import static ru.aston.finalproject.util.ConstantFields.EMAIL_FORM;
import static ru.aston.finalproject.util.ConstantFields.FIRST_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.util.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.util.ConstantFields.MIN_AGE;
import static ru.aston.finalproject.util.Message.AGE_CANNOT_BE_X_X_X;
import static ru.aston.finalproject.util.Message.INVALID_DATA_X;
import static ru.aston.finalproject.util.Message.X_CANNOT_BE_EMPTY;
import static ru.aston.finalproject.util.Message.X_IS_NOT_A_VALID_X;

public class ConstantMethods {

    private final static String NAME = "Name";
    private final static String EMAIL = "email";
    private final static String BELOW = "below";
    private final static String ABOVE = "above";
    private final static String DIGITS_REGS = "\\D+";
    private final static String NO_DIGITS_REGS = "\\d+";
    private final static String DIGITS = "digits";
    private final static String DATA_AT_INDEX_X = "data at index %d";

    public static void checkedStringOnEmpty(String string, String field) {
        if (string == null || string.trim().isEmpty()) {
            throw new AppException(String.format(X_CANNOT_BE_EMPTY, field));
        }
    }

    public static void checkedName(String name) {
        checkedStringOnEmpty(name, NAME);
        if (!name.equals(cleanStringFromDigit(name))) {
            throw new AppException(String.format(X_IS_NOT_A_VALID_X, name, NAME));
        }
    }

    public static void checkedEmail(String email) {
        checkedStringOnEmpty(email, EMAIL);
        if (!email.matches(EMAIL_FORM)) {
            throw new AppException(String.format(X_IS_NOT_A_VALID_X, email, EMAIL));
        }
    }

    public static void checkedAge(int age) {
        if (age < MIN_AGE) {
            throw new AppException(String.format(AGE_CANNOT_BE_X_X_X, BELOW, MIN_AGE, age));
        }
        if (age > MAX_AGE) {
            throw new AppException(String.format(AGE_CANNOT_BE_X_X_X, ABOVE, MAX_AGE, age));
        }
    }

    private static String createdStringOnlyDigits(String string) {
        checkedStringContainDigitsOnly(string);
        return string;
    }

    private static void checkedStringContainDigitsOnly(String string) {
        string = string.replaceAll(DIGITS_REGS, "").trim();
        checkedStringOnEmpty(string, DIGITS);
    }

    private static String cleanStringFromDigit(String string) {
        string = string.replaceAll(NO_DIGITS_REGS, "").trim();
        checkedStringOnEmpty(string, DIGITS);
        return string;
    }

    public static int createdDigitFromFirstInteger(String string) {
        String numbersOnly = createdStringOnlyDigits(string);
        checkedStringOnEmpty(numbersOnly, DIGITS);
        return Integer.parseInt(numbersOnly.trim().split(" ")[FIRST_ARRAY_COMPONENT]);
    }

    public static String exampleEntity(String fieldOne, String fieldTwo, int fieldInt) {
        return String.format(fieldOne + DELIMITER + fieldTwo + DELIMITER + fieldInt);
    }

    public static String[] preparingForParsing(String data, String delimiter) {
        String[] dataArray = data.split(delimiter);
        if (dataArray.length != LENGTH_PARAMETER) {
            throw new AppException(String.format(INVALID_DATA_X, data));
        }
        for (int i = 0; i < LENGTH_PARAMETER; i++) {
            checkedStringOnEmpty(dataArray[i], String.format(DATA_AT_INDEX_X, i));
        }
        return dataArray;
    }
}