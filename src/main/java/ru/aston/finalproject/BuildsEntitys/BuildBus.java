package ru.aston.finalproject.BuildsEntitys;

import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.validators.BusValidator;

public class BuildBus {

    private final BusValidator validator =  new BusValidator();

    public Entity buildConcreteBus(String model, String mileageInKilometers, int number) {
        return Entity.
                builder().
                fieldOne(model).
                fieldTwo(mileageInKilometers).
                fieldInt(number).
                build(validator);
    }
}
