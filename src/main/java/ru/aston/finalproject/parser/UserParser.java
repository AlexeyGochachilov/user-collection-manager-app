package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.statictools.ConstantFields.DELIMITER;
import static ru.aston.finalproject.statictools.ConstantFields.ONE;
import static ru.aston.finalproject.statictools.ConstantFields.TWO;
import static ru.aston.finalproject.statictools.ConstantFields.ZERO;
import static ru.aston.finalproject.statictools.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.statictools.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.statictools.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.statictools.ConstantMethods.preparingForParsing;

public class UserParser implements Parsing<User> {

    BuildUser buildConcreteEntity = new BuildUser();

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
        checkedStringOnEmpty(data, "data in parser");
        String[] dataArray = preparingForParsing(data, delimiter);
        String name = dataArray[ZERO];
        String email = dataArray[ONE];
        int age = createdDigitFromFirstInteger(dataArray[TWO]);
        return buildConcreteEntity.capitalizeNameAndNormalizedEmail(name, email, age);
    }
}
