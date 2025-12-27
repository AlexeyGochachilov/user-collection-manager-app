package ru.aston.finalproject.parser;

import ru.aston.finalproject.BuildsEntitys.BuildBus;
import ru.aston.finalproject.entity.Entity;

import static ru.aston.finalproject.constants.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedMileageInKilometers;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;

public class BusParser implements Parsing<Entity> {

    private final BuildBus buildBus;
    private String model;
    private String mileageInKilometers;
    private int number;

    public BusParser() {
        this.buildBus = new BuildBus();
    }

    @Override
    public Entity parse(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = data.split(delimiter);

        if (dataArray.length != LENGTH_PARAMETER) {
            throw new IllegalArgumentException(String.format("Invalid data %s", data));
        }

        createdModel(dataArray[ZERO]);
        createdMileageInKilometers(dataArray[ONE]);
        createdNumberFromFirstInteger(dataArray[TWO]);

        return buildBus.buildConcreteBus(model, mileageInKilometers, number);
    }

    private void createdModel(String model) {
        checkedStringOnEmpty(model);
        this.model = model;
    }

    private void createdMileageInKilometers(String mileageInKilometers) {
        checkedMileageInKilometers(mileageInKilometers);
        this.mileageInKilometers = mileageInKilometers;
    }

    private void createdNumberFromFirstInteger(String number) {
        this.number = createdDigitFromFirstInteger(number);
    }
}
