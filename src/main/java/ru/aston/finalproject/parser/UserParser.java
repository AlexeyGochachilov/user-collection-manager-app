package ru.aston.finalproject.parser;

import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.util.ConstantFields.DELIMITER;
import static ru.aston.finalproject.util.ConstantFields.SECOND_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantFields.THIRD_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantFields.FIRST_ARRAY_COMPONENT;
import static ru.aston.finalproject.util.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.util.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.util.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.util.ConstantMethods.preparingForParsing;
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
}
