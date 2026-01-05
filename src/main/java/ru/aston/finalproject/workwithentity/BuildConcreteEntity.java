package ru.aston.finalproject.workwithentity;

import ru.aston.finalproject.validators.BusValidator;
import ru.aston.finalproject.validators.UserValidator;

public class BuildConcreteEntity {

    public Bus buildBus(String model, String mileageInKilometers, int number) {
        return Bus.builder().fieldOne(model).fieldTwo(mileageInKilometers).fieldInt(number).build(new BusValidator());
    }

    public User buildUser(String name, String email, int age) {
        return User.builder().fieldOne(name).fieldTwo(email).fieldInt(age).build(new UserValidator());
    }
}
