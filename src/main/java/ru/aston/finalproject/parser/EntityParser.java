package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.BuildConcreteEntity;
import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.validators.Validator;

import static ru.aston.finalproject.constants.ConstantFields.DELIMITER;
import static ru.aston.finalproject.constants.ConstantFields.LENGTH_PARAMETER;
import static ru.aston.finalproject.constants.ConstantFields.ONE;
import static ru.aston.finalproject.constants.ConstantFields.TWO;
import static ru.aston.finalproject.constants.ConstantFields.ZERO;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.createdDigitFromFirstInteger;
import static ru.aston.finalproject.constants.ConstantMethods.exampleEntity;

public class EntityParser implements Parsing {

    private final BuildConcreteEntity buildEntity;
    protected Validator validator;
    private String fieldOne;
    private String fieldTwo;
    private int fieldInt;

    public EntityParser() {
        buildEntity = new BuildConcreteEntity();
    }

    @Override
    public String parseEntityToString(Entity entity) {
        return exampleEntity(fieldOne, fieldTwo, fieldInt);
    }

    @Override
    public Entity parseStringToEntity(String data) {
        return parseStringToEntity(data, DELIMITER);
    }

    @Override
    public Entity parseStringToEntity(String data, String delimiter) {

        checkedStringOnEmpty(data);
        String[] dataArray = data.split(delimiter);

        if (dataArray.length != LENGTH_PARAMETER) {
            throw new IllegalArgumentException(String.format("Invalid data %s", data));
        }

        createdFieldOne(dataArray[ZERO]);
        createdFieldTwo(dataArray[ONE]);
        createdIntFromFirstInteger(dataArray[TWO]);

        return buildEntity.buildCustomEntity(fieldOne, fieldTwo, fieldInt, validator);
    }

    private void createdFieldOne(String string) {
        checkedStringOnEmpty(string);
        this.fieldOne = string;
    }

    private void createdFieldTwo(String string) {
        checkedStringOnEmpty(string);
        this.fieldTwo = string;
    }

    private void createdIntFromFirstInteger(String string) {
        this.fieldInt = createdDigitFromFirstInteger(string);
    }
}