package ru.aston.finalproject.entity.user;

import net.datafaker.Faker;

import static ru.aston.finalproject.util.ConstantFields.MAX_AGE_EXCLUDED;
import static ru.aston.finalproject.util.ConstantFields.MIN_AGE;

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
