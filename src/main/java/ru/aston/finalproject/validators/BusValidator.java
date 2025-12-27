package ru.aston.finalproject.validators;

import static ru.aston.finalproject.constants.ConstantMethods.checkedMileageInKilometers;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedZero;

public class BusValidator implements Validator {

    @Override
    public void validateEntity(String model, String mileageInKilometers, int number) {
        checkedStringOnEmpty(model);
        checkedMileageInKilometers(mileageInKilometers);
        checkedZero(number);
    }
}
