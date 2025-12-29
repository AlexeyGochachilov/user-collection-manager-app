package ru.aston.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.aston.finalproject.validators.Validator;

import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedZero;

@Getter
@EqualsAndHashCode
public class Entity implements Comparable<Entity> {

    private final String fieldOne;
    private final String fieldTwo;
    private final int fieldInt;

    private Entity(Builder builder) {
        this.fieldOne = builder.fieldOne;
        this.fieldTwo = builder.fieldTwo;
        this.fieldInt = builder.fieldInt;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", fieldOne, fieldTwo, fieldInt);
    }

    @Override
    public int compareTo(Entity o) {
        if (!this.fieldOne.equals(o.fieldOne)) {
            return this.fieldOne.compareTo(o.fieldOne);
        } else if (!this.fieldTwo.equals(o.fieldTwo)) {
            return this.fieldTwo.compareTo(o.fieldTwo);
        } else {
            return Integer.compare(this.fieldInt, o.fieldInt);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String fieldOne;
        private String fieldTwo;
        private int fieldInt;

        public Builder() {
        }

        public Builder fieldOne(String fieldOne) {
            checkedStringOnEmpty(fieldOne);
            this.fieldOne = fieldOne;
            return this;
        }

        public Builder fieldTwo(String fieldTwo) {
            checkedStringOnEmpty(fieldTwo);
            this.fieldTwo = fieldTwo;
            return this;
        }

        public Builder fieldInt(int intValue) {
            checkedZero(intValue);
            this.fieldInt = intValue;
            return this;
        }

        public Entity build(Validator validator) {
            validator.validate(fieldOne, fieldTwo, fieldInt);
            return new Entity(this);
        }
    }
}