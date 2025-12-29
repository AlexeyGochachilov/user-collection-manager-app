package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.constants.ConstantFields.DELIMITER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.constants.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.constants.ConstantMethods.preparingForParsing;

public class UserParser implements Parsing<User> {

    private String name;
    private String email;
    private int age;

    @Override
    public String parseToString(Entity entity) {
        return exampleEntity(name, email, age);
    }

    @Override
    public User parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public User parse(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = preparingForParsing(data, delimiter);
        name = dataArray[ZERO];
        email = dataArray[ONE];
        age = createdDigitFromFirstInteger(dataArray[TWO]);
        return User.build(name, email, age);
    }
}
