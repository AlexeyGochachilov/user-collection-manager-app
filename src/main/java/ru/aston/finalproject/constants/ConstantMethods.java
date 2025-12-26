package ru.aston.finalproject.constants;

import static ru.aston.finalproject.constants.ConstantFields.EMAIL_FORM;
import static ru.aston.finalproject.constants.ConstantFields.MIN_AGE;

public class ConstantMethods {

    public static void validateUser (String username, String email, int age){
        checkedStringOnEmpty(username);
        checkedStringOnEmpty(email);
        checkedEmail(email);
        validateAge(age);
    }

    public static void checkedStringOnEmpty(String string){
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", string));
        }
    }

    public static void validateAge(int age) {
        if (age <= MIN_AGE) {
            throw new IllegalArgumentException(String.format("Age cannot be below %d", MIN_AGE));
        }
    }

    public static void checkedEmail(String email) {
        if (!email.matches(EMAIL_FORM)) {
            throw new IllegalArgumentException(String.format("Invalid email %s", email));
        }
    }
}