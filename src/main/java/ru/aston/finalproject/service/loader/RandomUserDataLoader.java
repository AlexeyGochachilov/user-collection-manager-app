package ru.aston.finalproject.service.loader;

import net.datafaker.Faker;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.validators.UserValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomUserDataLoader implements DataLoader<User> {
    private final static Integer MIN_AGE = 1;
    private final static Integer MAX_AGE = 99;
    private final Faker faker = new Faker();
    private final UserValidator validator;

    public RandomUserDataLoader(UserValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<User> loadEntityList(Integer size) {
        return Stream.generate(User::builder)
                .map(builder -> builder.name(faker.name().firstName()))
                .map(builder -> builder.email(faker.internet().emailAddress()))
                .map(builder -> builder.age(faker.number().numberBetween(MIN_AGE, MAX_AGE)))
                .map(builder -> builder.build(validator))
                .limit(size)
                .collect(Collectors.toList());
    }
}
