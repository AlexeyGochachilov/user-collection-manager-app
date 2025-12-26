package ru.aston.finalproject.constants;

public class ConstantMethods {

    public static void checkedStringOnEmpty(String string){
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", string));
        }
    }
}
