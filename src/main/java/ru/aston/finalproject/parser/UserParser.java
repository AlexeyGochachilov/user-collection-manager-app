package ru.aston.finalproject.parser;

import ru.aston.finalproject.workwithentity.BuildConcreteEntity;
import ru.aston.finalproject.workwithentity.User;

import static ru.aston.finalproject.constants.ConstantFields.DELIMITER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.constants.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.constants.ConstantMethods.preparingForParsing;

public class UserParser implements Parsing<User> {

    BuildConcreteEntity buildConcreteEntity = new BuildConcreteEntity();

    @Override
    public String parseToString(User user) {
        return exampleEntity(user.getName(), user.getEmail(), user.getAge());
    }

    @Override
    public User parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public User parse(String data, String delimiter) {
        checkedStringOnEmpty(data);
        String[] dataArray = preparingForParsing(data, delimiter);
        String name = dataArray[ZERO];
        String email = dataArray[ONE];
        int age = createdDigitFromFirstInteger(dataArray[TWO]);
        return buildConcreteEntity.buildUser(name, email, age);
    }
}
