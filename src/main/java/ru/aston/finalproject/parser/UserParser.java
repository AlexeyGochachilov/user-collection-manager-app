package ru.aston.finalproject.parser;

import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.entity.user.BuildUser;
import ru.aston.finalproject.entity.user.User;

import static ru.aston.finalproject.util.ConstantFields.DIGITS;
import static ru.aston.finalproject.util.ConstantFields.SPACE;
import static ru.aston.finalproject.util.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.util.Message.INVALID_DATA_X;
import static ru.aston.finalproject.util.Message.USER_CANNOT_BE_NULL;

public class UserParser implements Parsing<User> {

    private static final String NO_DIGITS_REGS = "\\D+";
    private static final String DELIMITER = " : ";
    public static final String USER_FORMAT = String.format("name%semail%sage", DELIMITER, DELIMITER);

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
        checkedStringContainDigitsOnly(string);
        return Integer.parseInt(string.trim().split(SPACE)[0]);
    }

    private void checkedStringContainDigitsOnly(String string) {
        string = string.replaceAll(NO_DIGITS_REGS, "").trim();
        checkedStringOnEmpty(string, DIGITS);
    }
}
