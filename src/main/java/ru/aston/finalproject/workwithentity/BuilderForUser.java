package ru.aston.finalproject.workwithentity;

import ru.aston.finalproject.validators.Validator;

public class BuilderForUser extends BuilderIMPL<User> {

    @Override
    public User build(Validator<User> validator) {
        validator.validate(super.fieldOne, super.fieldTwo, super.fieldInt);
        return new User(this);
    }
}
