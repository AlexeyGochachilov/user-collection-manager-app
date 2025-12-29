package ru.aston.finalproject.entity;

import ru.aston.finalproject.validators.BusValidator;
import ru.aston.finalproject.validators.UserValidator;
import ru.aston.finalproject.validators.Validator;

public class BuildConcreteEntity {

    public Entity buildBus(String model, String mileageInKilometers, int number) {
        return buildCustomEntity(model, mileageInKilometers, number, new BusValidator());
    }

    public Entity buildUser(String name, String email, int age) {
        return buildCustomEntity(name, email, age, new UserValidator());
    }

    public Entity buildCustomEntity(String fieldOne, String fieldTwo, int fieldInt, Validator validator) {
        return Entity.
                builder().
                fieldOne(fieldOne).
                fieldTwo(fieldTwo).
                fieldInt(fieldInt).
                build(validator);
    }
}
