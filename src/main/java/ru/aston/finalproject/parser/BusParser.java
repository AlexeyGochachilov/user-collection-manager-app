package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Bus;
import ru.aston.finalproject.entity.Entity;

import static ru.aston.finalproject.constants.ConstantFields.DELIMITER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.constants.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.constants.ConstantMethods.preparingForParsing;

public class BusParser implements Parsing<Bus> {

    private String model;
    private String mileageInKilometers;
    private int number;

    @Override
    public String parseToString(Entity entity) {
        return exampleEntity(model, mileageInKilometers, number);
    }

    @Override
    public Bus parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public Bus parse(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = preparingForParsing(data, delimiter);
        model = dataArray[ZERO];
        mileageInKilometers = dataArray[ONE];
        number = createdDigitFromFirstInteger(dataArray[TWO]);
        return Bus.build(model, mileageInKilometers, number);
    }
}
