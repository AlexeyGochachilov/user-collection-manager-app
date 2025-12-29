package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildConcreteEntity;
import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.validators.Validator;

import static ru.aston.finalproject.constants.ConstantFields.DELIMITER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.constants.ConstantMethods.exampleEntity;
import static ru.aston.finalproject.constants.ConstantMethods.preparingForParsing;

public class EntityParser implements Parsing<Entity> {

    private final BuildConcreteEntity buildEntity;
    private final Validator validator;
    private String fieldOne;
    private String fieldTwo;
    private int fieldInt;

    public EntityParser(Validator validator) {
        this.validator = validator;
        buildEntity = new BuildConcreteEntity();
    }

    @Override
    public String parseToString(Entity entity) {
        return exampleEntity(fieldOne, fieldTwo, fieldInt);
    }

    @Override
    public Entity parse(String data) {
        return parse(data, DELIMITER);
    }

    @Override
    public Entity parse(String data, String delimiter) {
        checkedStringOnEmpty(data);
        String[] dataArray = preparingForParsing(data, delimiter);
        fieldOne = dataArray[ZERO];
        fieldTwo = dataArray[ONE];
        fieldInt = createdDigitFromFirstInteger(dataArray[TWO]);
        return buildEntity.buildCustomEntity(fieldOne, fieldTwo, fieldInt, validator);
    }
}