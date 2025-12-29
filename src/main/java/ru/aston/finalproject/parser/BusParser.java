package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.validators.BusValidator;

public class BusParser extends EntityParser {

    public BusParser() {
        super.validator = new BusValidator();
    }

    public Entity parsingStringToBus(String data, String delimiter) {
        return parseStringToEntity(data, delimiter);
    }

    public Entity parsingStringToBus(String data) {
        return parseStringToEntity(data);
    }

}
