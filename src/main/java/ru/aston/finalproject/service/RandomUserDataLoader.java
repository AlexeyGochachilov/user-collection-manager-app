package ru.aston.finalproject.service;

import net.datafaker.Faker;
import ru.aston.finalproject.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.aston.finalproject.util.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.util.ConstantFields.MIN_AGE;
import static ru.aston.finalproject.util.ConstantFields.ONE;

public class RandomUserDataLoader implements DataLoader<User> {

    private final Faker faker = new Faker();

    public RandomUserDataLoader() {

    }

    @Override
    public List<User> loadEntityList(Integer size) {
        return Stream.generate(User::builder)
                .map(builder -> builder.setName(faker.name().firstName()))
                .map(builder -> builder.setEmail(faker.internet().emailAddress()))
                .map(builder -> builder.setAge(faker.number().numberBetween(MIN_AGE, MAX_AGE)))
                .map(User.Builder::build)
                .limit(size)
                .collect(Collectors.toList());
    }
}
