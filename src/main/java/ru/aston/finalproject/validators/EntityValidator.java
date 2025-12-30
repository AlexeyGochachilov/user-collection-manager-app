package ru.aston.finalproject.validators;

import ru.aston.finalproject.entity.Entity;

import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedZero;

public class EntityValidator implements Validator<Entity> {

    public void validate(String fieldOne, String fieldTwo, int fieldInt) {
        checkedStringOnEmpty(fieldOne);
        checkedStringOnEmpty(fieldTwo);
        checkedZero(fieldInt);
    }
}
