package ru.aston.finalproject.validators;

import ru.aston.finalproject.workwithentity.Bus;

import static ru.aston.finalproject.constants.ConstantMethods.checkedMileageInKilometers;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedZero;

public class BusValidator implements Validator<Bus> {

    @Override
    public void validate(String model, String mileageInKilometers, int number) {
        checkedStringOnEmpty(model, "model");
        checkedMileageInKilometers(mileageInKilometers);
        checkedZero(number);
    }
}
