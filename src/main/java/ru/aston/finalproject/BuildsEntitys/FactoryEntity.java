package ru.aston.finalproject.BuildsEntitys;

import ru.aston.finalproject.entity.Entity;

public class FactoryEntity {

    private static final BuildBus BUS = new BuildBus();
    private static final BuildUser USER = new BuildUser();

    public static Entity createBus(String model, String mileageInKilometers, int number) {
        return BUS.buildConcreteBus(model, mileageInKilometers, number);
    }

    public static Entity createUser(String name, String email, int age) {
        return USER.buildConcreteUser(name, email, age);
    }
}
