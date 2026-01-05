package ru.aston.finalproject.workwithentity;

import lombok.Getter;
import ru.aston.finalproject.validators.Validator;

@Getter
public abstract class BuilderIMPL<T> implements Builder<T> {

    String fieldOne;
    String fieldTwo;
    int fieldInt;

    BuilderIMPL() {
    }

    public BuilderIMPL<T> fieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
        return this;
    }

    public BuilderIMPL<T> fieldTwo(String fieldTwo) {
        this.fieldTwo = fieldTwo;
        return this;
    }

    public BuilderIMPL<T> fieldInt(int intValue) {
        this.fieldInt = intValue;
        return this;
    }

    public abstract T build(Validator<T> validator);
}
