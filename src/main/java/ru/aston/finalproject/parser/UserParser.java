package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.Entity;

import static ru.aston.finalproject.constants.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;

public class UserParser implements Parsing<Entity> {

    private final BuildUser buildUser;
    private String name;
    private String email;
    private int age;

    public UserParser() {
        buildUser = new BuildUser();
    }

    @Override
    public Entity parse(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = data.split(delimiter);
        int one = 1;
        int two = 2;

        if (dataArray.length != LENGTH_PARAMETER) {
            throw new IllegalArgumentException(String.format("Invalid data %s", data));
        }

        createdName(dataArray[ZERO]);
        createdEmail(dataArray[one]);
        createdAgeFromFirstInteger(dataArray[two]);

        return buildUser.buildConcreteUser(name, email, age);
    }

    private void createdName(String name) {
        checkedStringOnEmpty(name);
        this.name = name.trim();
    }

    private void createdEmail(String email) {
        checkedStringOnEmpty(email);
        checkedEmail(email);
        this.email = email.trim();
    }

    private void createdAgeFromFirstInteger(String string) {
        String numbersOnly = string.replaceAll("\\D+", " ").trim();
        checkedStringForNumber(numbersOnly);
        this.age = Integer.parseInt(numbersOnly.split(" ")[0]);
    }

    private void checkedStringForNumber(String numbersOnly) {
        if (numbersOnly.isEmpty()) {
            throw new IllegalArgumentException("No numbers found in text: " + numbersOnly);
        }
    }
}