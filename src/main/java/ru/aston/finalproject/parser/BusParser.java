package ru.aston.finalproject.parser;

import ru.aston.finalproject.workwithentity.BuildConcreteEntity;
import ru.aston.finalproject.workwithentity.Bus;

import static ru.aston.finalproject.constants.ConstantFields.DELIMITER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.constants.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.constants.ConstantMethods.preparingForParsing;

public class BusParser implements Parsing<Bus> {

    BuildConcreteEntity buildConcreteEntity = new BuildConcreteEntity();

    @Override
    public String parseToString(Bus bus) {
        return exampleEntity(bus.getModel(),
                bus.getMileageInKilometers(),
                bus.getNumber());
    }

    @Override
    public Bus parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public Bus parse(String data, String delimiter) {
        checkedStringOnEmpty(data);
        String[] dataArray = preparingForParsing(data, delimiter);
        String model = dataArray[ZERO];
        String mileageInKilometers = dataArray[ONE];
        int number = createdDigitFromFirstInteger(dataArray[TWO]);
        return buildConcreteEntity.buildBus(model, mileageInKilometers, number);
    }
}
