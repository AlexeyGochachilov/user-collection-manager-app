package ru.aston.finalproject.util;

public class ConstantFields {

    public static final int MIN_AGE = 1;
    public static final int MAX_AGE = 120;
    public static final int FIRST_ARRAY_COMPONENT = 0;
    public static final int SECOND_ARRAY_COMPONENT = 1;
    public static final int THIRD_ARRAY_COMPONENT = 2;
    public static final int LENGTH_PARAMETER = 3;

    public static final String EMAIL_FORM = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$";
    public static final String DELIMITER = " : ";
    public static final String USER_FORMAT =
            String.format("name%semail%sage", DELIMITER, DELIMITER);
}
