package ru.aston.finalproject.util;

public class ConstantFields {

    public static class ForUser {
        public final static String EMAIL_FORM = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$";
        public final static String NO_DIGITS_REGS = "\\d+";
    }

    public static class ForUserAndRandomUserDataLoader {
        public final static int MIN_AGE = 1;
        public final static int MAX_AGE = 120;
    }

    public static class ForUserAndUserParser {
        public final static String DIGITS = "digits";
    }

    public static class ForUserParser {
        public final static String DIGITS_REGS = "\\D+";
        public final static String DELIMITER = " : ";
    }

    public final static String SPACE = "\\s";
    public final static String USER_FORMAT = String.format
            ("name%semail%sage", ForUserParser.DELIMITER, ForUserParser.DELIMITER);
}
