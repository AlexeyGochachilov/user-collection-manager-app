package ru.aston.finalproject.parser;

import ru.aston.finalproject.validators.UserValidator;

public class UserParser extends EntityParser {

    public UserParser() {
        super.validator = new UserValidator();
    }
}
