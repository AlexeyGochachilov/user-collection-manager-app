package ru.aston.finalproject.parser;

import ru.aston.finalproject.validators.BusValidator;

public class BusParser extends EntityParser {

    public BusParser() {
        super.validator = new BusValidator();
    }
}
