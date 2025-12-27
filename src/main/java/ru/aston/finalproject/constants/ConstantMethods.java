package ru.aston.finalproject.constants;

import static ru.aston.finalproject.constants.ConstantFields.EMAIL_FORM;
import static ru.aston.finalproject.constants.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.constants.ConstantFields.MIN_AGE;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;

public class ConstantMethods {

    public static void checkedStringOnEmpty(String string){
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", string));
        }
    }

    public static void validateAge(int age) {
        if (age <= MIN_AGE) {
            throw new IllegalArgumentException(String.format("Age cannot be below %d", MIN_AGE));
        }
        if (age >= MAX_AGE) {
            throw new IllegalArgumentException(String.format("Age cannot be above %d", MAX_AGE));
        }
    }

    public static void validateInt(int intValue) {
        if (intValue == ZERO) {
            throw new IllegalArgumentException(String.format("%s cannot be zero", intValue));
        }
    }

    public static void checkedEmail(String email) {
        if (!email.matches(EMAIL_FORM)) {
            throw new IllegalArgumentException(String.format("Invalid email %s", email));
        }
    }
}