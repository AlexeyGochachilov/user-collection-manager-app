package ru.aston.finalproject.constants;

import java.util.LinkedList;
import java.util.List;

public class ConstantFields {

    public static final int MIN_AGE = 1;
    public static final int MAX_AGE = 120;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int LENGTH_PARAMETER = 3;

    public static final String EMAIL_FORM = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$";
    public static final String KM = "km.";

    public static List<Exception> EXCEPTION = new LinkedList<>();
}
