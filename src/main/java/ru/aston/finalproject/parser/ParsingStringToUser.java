package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildConcreteUser;
import ru.aston.finalproject.entity.User;

import static ru.aston.finalproject.constants.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;

public class ParsingStringToUser implements Parsing<User> {

    private final BuildConcreteUser buildUser;
    private String name;
    private String email;
    private int age;

    public ParsingStringToUser() {
        buildUser = new BuildConcreteUser();
    }

    @Override
    public User parse(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = data.split(delimiter);
        int zero = 0;
        int one = 1;
        int two = 2;

        if (dataArray.length != LENGTH_PARAMETER) {
            throw new IllegalArgumentException(String.format("Invalid data %s", data));
        }

        createdName(dataArray[zero]);
        createdEmail(dataArray[one]);
        createdAgeFromFirstInteger(dataArray[two]);

        return buildUser.buildConcreteUser(name, email, age);
    }

    private void createdName(String name) {
        checkedStringOnEmpty(name);
        this.name = name.trim();
    }

    private void createdEmail(String email) {
        checkedEmail(email);
        this.email = email.trim();
    }

    private void checkedEmail(String email) {
        if (email.indexOf('@') != email.lastIndexOf('@')) {
            throw new IllegalArgumentException(String.format("Invalid email %s", email));
        }
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
