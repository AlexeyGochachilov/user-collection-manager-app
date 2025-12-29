package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildConcreteEntity;
import ru.aston.finalproject.entity.Entity;

import static ru.aston.finalproject.constants.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;

public class UserParser implements Parsing<Entity> {

    private final BuildConcreteEntity buildUser;
    private String name;
    private String email;
    private int age;

    public UserParser() {
        buildUser = new BuildConcreteEntity();
    }

    @Override
    public Entity parse(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = data.split(delimiter);

        if (dataArray.length != LENGTH_PARAMETER) {
            throw new IllegalArgumentException(String.format("Invalid data %s", data));
        }

        createdName(dataArray[ZERO]);
        createdEmail(dataArray[ONE]);
        createdAgeFromFirstInteger(dataArray[TWO]);

        return buildUser.buildCustomEntity(name, email, age);
    }

    private void createdName(String name) {
        checkedStringOnEmpty(name);
        this.name = name.trim();
    }

    private void createdEmail(String email) {
        checkedEmail(email);
        this.email = email.trim();
    }

    private void createdAgeFromFirstInteger(String string) {
        this.age = createdDigitFromFirstInteger(string);
    }
}