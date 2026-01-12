package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.staticTools.ConstantFields.DELIMITER;
import static ru.aston.finalproject.staticTools.ConstantFields.ONE;
import static ru.aston.finalproject.staticTools.ConstantFields.TWO;
import static ru.aston.finalproject.staticTools.ConstantFields.ZERO;
import static ru.aston.finalproject.staticTools.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.staticTools.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.staticTools.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.staticTools.ConstantMethods.preparingForParsing;

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
