package ru.aston.finalproject.parser;

import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.staticTools.ConstantFields.DIGITS;
import static ru.aston.finalproject.staticTools.ConstantFields.SPACE;
import static ru.aston.finalproject.staticTools.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.staticTools.Message.INVALID_DATA_X;
import static ru.aston.finalproject.staticTools.Message.USER_CANNOT_BE_NULL;

public class UserParser implements Parsing<User> {

    private final static String DIGITS_REGS = "\\D+";
    private final static String DELIMITER = " : ";
    public final static String USER_FORMAT = String.format("name%semail%sage", DELIMITER, DELIMITER);

    @Override
    public String parseToString(User user) {
        if (user == null) {
            throw new AppException(USER_CANNOT_BE_NULL);
        }
        return exampleEntity(user.getName(), user.getEmail(), user.getAge());
    }

    private String exampleEntity(String fieldOne, String fieldTwo, int fieldInt) {
        return String.format(fieldOne + DELIMITER + fieldTwo + DELIMITER + fieldInt);
    }

    @Override
    public User parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public User parse(String data, String delimiter) {

        checkedStringOnEmpty(data, "data in parser");

        BuildUser buildConcreteEntity = new BuildUser();
        String[] dataArray = preparingForParsing(data, delimiter);

        String name = dataArray[0].trim();
        String email = dataArray[1].trim();
        int age = createdDigitFromFirstInteger(dataArray[2].trim());

        return buildConcreteEntity.capitalizeNameAndNormalizedEmail(name, email, age);
    }

    private String[] preparingForParsing(String data, String delimiter) {
        String DATA_AT_INDEX_X = "data at index %d";
        String[] dataArray = data.split(delimiter);
        int LENGTH_PARAMETER = 3;

        if (dataArray.length != LENGTH_PARAMETER) {
            throw new AppException(String.format(INVALID_DATA_X, data));
        }
        for (int i = 0; i < LENGTH_PARAMETER; i++) {
            checkedStringOnEmpty(dataArray[i], String.format(DATA_AT_INDEX_X, i));
        }
        return dataArray;
    }

    private int createdDigitFromFirstInteger(String string) {
        String numbersOnly = createdStringOnlyDigits(string);
        checkedStringOnEmpty(numbersOnly, DIGITS);
        return Integer.parseInt(numbersOnly.trim().split(SPACE)[0]);
    }

    private String createdStringOnlyDigits(String string) {
        checkedStringContainDigitsOnly(string);
        return string;
    }

    private void checkedStringContainDigitsOnly(String string) {
        string = string.replaceAll(DIGITS_REGS, "").trim();
        checkedStringOnEmpty(string, DIGITS);
    }
}
