package ru.aston.finalproject.entity;

import net.datafaker.Faker;

import static ru.aston.finalproject.staticTools.ConstantFields.MAX_AGE_EXCLUDED;
import static ru.aston.finalproject.staticTools.ConstantFields.MIN_AGE;

public class UserDataFaker {
    private final Faker dataFaker = new Faker();

    public String getRandomUserName() {
        return dataFaker.name().firstName();
    }

    public String getRandomUserEmail() {
        return dataFaker.internet().emailAddress();
    }

    public Integer getRandomUserAge() {
        return dataFaker.number().numberBetween(MIN_AGE, MAX_AGE_EXCLUDED);
    }
}
