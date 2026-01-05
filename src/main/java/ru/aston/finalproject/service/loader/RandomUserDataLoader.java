package ru.aston.finalproject.service.loader;

import net.datafaker.Faker;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.validators.UserValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.aston.finalproject.constants.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.constants.ConstantFields.MIN_AGE;

public class RandomUserDataLoader implements DataLoader<User> {
    private final Faker faker = new Faker();
    private final UserValidator validator;

    public RandomUserDataLoader(UserValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<User> loadEntityList(Integer size, AppRequest request) {
        return Stream.generate(User::builder)
                .map(builder -> builder.name(faker.name().firstName()))
                .map(builder -> builder.email(faker.internet().emailAddress()))
                .map(builder -> builder.age(faker.number().numberBetween(MIN_AGE, MAX_AGE)))
                .map(builder -> builder.build(validator))
                .limit(size)
                .collect(Collectors.toList());
    }
}
