package ru.aston.finalproject.parser;

import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.util.ConstantFields.DELIMITER;
import static ru.aston.finalproject.util.ConstantFields.DIGITS;
import static ru.aston.finalproject.util.ConstantFields.DIGITS_REGS;
import static ru.aston.finalproject.util.ConstantFields.FIRST_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.util.ConstantFields.SECOND_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantFields.THIRD_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.util.Message.INVALID_DATA_X;
import static ru.aston.finalproject.util.Message.USER_CANNOT_BE_NULL;

public class UserParser implements Parsing<User> {

    BuildUser buildConcreteEntity = new BuildUser();

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
        String[] dataArray = preparingForParsing(data, delimiter);
        String name = dataArray[FIRST_ARRAY_COMPONENT].trim();
        String email = dataArray[SECOND_ARRAY_COMPONENT].trim();
        int age = createdDigitFromFirstInteger(dataArray[THIRD_ARRAY_COMPONENT].trim());
        return buildConcreteEntity.capitalizeNameAndNormalizedEmail(name, email, age);
    }

    private String[] preparingForParsing(String data, String delimiter) {
        String DATA_AT_INDEX_X = "data at index %d";
        String[] dataArray = data.split(delimiter);
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
        return Integer.parseInt(numbersOnly.trim().split(" ")[FIRST_ARRAY_COMPONENT]);
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
