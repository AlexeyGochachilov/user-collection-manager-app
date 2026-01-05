package ru.aston.finalproject.workwithentity;

import ru.aston.finalproject.validators.Validator;

public interface Builder<T> {

    public Builder<T> fieldOne(String fieldOne);

    public Builder<T> fieldTwo(String fieldTwo);

    public Builder<T> fieldInt(int intValue);

    public T build(Validator<T> validator);
}
