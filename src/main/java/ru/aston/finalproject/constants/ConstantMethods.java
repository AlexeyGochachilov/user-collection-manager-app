package ru.aston.finalproject.constants;

import static ru.aston.finalproject.constants.ConstantFields.EMAIL_FORM;
import static ru.aston.finalproject.constants.ConstantFields.KM;
import static ru.aston.finalproject.constants.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.constants.ConstantFields.MIN_AGE;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;

public class ConstantMethods {

    public static void checkedStringOnEmpty(String string){
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", string));
        }
    }

    public static void checkedName(String name){
        checkedStringOnEmpty(name);
        if (!name.equals(createdStringWordsOnly(name))){
            throw new IllegalArgumentException(String.format("%s is not a valid name", name));
        }
    }

    public static void checkedEmail(String email) {
        checkedStringOnEmpty(email);
        if (!email.matches(EMAIL_FORM)) {
            throw new IllegalArgumentException(String.format("Invalid email %s", email));
        }
    }

    public static void checkedAge(int age) {
        if (age <= MIN_AGE) {
            throw new IllegalArgumentException(String.format("Age cannot be below %d", MIN_AGE));
        }
        if (age >= MAX_AGE) {
            throw new IllegalArgumentException(String.format("Age cannot be above %d", MAX_AGE));
        }
    }

    public static void checkedZero(int intValue) {
        if (intValue == ZERO) {
            throw new IllegalArgumentException(String.format("%s cannot be zero", intValue));
        }
    }

    public static void checkedMileageInKilometers(String mileageInKilometers){
        String km = createdStringWordsOnly(mileageInKilometers);
        if (!km.equals(KM)) {
            throw new IllegalArgumentException(
                    String.format("Invalid args, mileageInKilometers need content %s", KM));
        }
        String digits = createdStringDigitsOnly(mileageInKilometers);
    }

    public static String createdStringDigitsOnly(String string) {
        string = string.replaceAll("\\D+", " ").trim();
        checkedStringOnEmpty(string);
        return string;
    }

    public static String createdStringWordsOnly(String string) {
        string = string.replaceAll("\\d+", " ").trim();
        checkedStringOnEmpty(string);
        return string;
    }
}