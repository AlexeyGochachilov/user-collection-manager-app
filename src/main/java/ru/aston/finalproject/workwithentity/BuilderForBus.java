package ru.aston.finalproject.workwithentity;

import ru.aston.finalproject.validators.Validator;

public class BuilderForBus extends BuilderIMPL<Bus> {

    @Override
    public Bus build(Validator<Bus> validator) {
        validator.validate(super.fieldOne, super.fieldTwo, super.fieldInt);
        return new Bus(this);
    }
}
