package ru.aston.finalproject.workwithentity;

import lombok.Getter;
import ru.aston.finalproject.validators.Validator;

import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedZero;

@Getter
public abstract class BuilderIMPL<T> implements Builder<T> {

    String fieldOne;
    String fieldTwo;
    int fieldInt;

    BuilderIMPL() {
    }

    public BuilderIMPL<T> fieldOne(String fieldOne) {
//        checkedStringOnEmpty(fieldOne);
        this.fieldOne = fieldOne;
        return this;
    }

    public BuilderIMPL<T> fieldTwo(String fieldTwo) {
//        checkedStringOnEmpty(fieldTwo);
        this.fieldTwo = fieldTwo;
        return this;
    }

    public BuilderIMPL<T> fieldInt(int intValue) {
//        checkedZero(intValue);
        this.fieldInt = intValue;
        return this;
    }

    public abstract T build(Validator<T> validator);
}
