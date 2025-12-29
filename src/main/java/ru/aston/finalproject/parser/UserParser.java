package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.validators.UserValidator;

public class UserParser extends EntityParser {

    public UserParser() {
        super.validator = new UserValidator();
    }

    public Entity parsingStringToUser(String data, String delimiter) {
        return parseStringToEntity(data, delimiter);
    }

    public Entity parsingStringToEntity(String data) {
        return parseStringToEntity(data);
    }
}
