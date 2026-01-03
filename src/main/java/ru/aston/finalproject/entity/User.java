package ru.aston.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.aston.finalproject.validators.UserValidator;
import ru.aston.finalproject.validators.Validator;

import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedZero;

@Getter
@EqualsAndHashCode
public class User implements Comparable<User> {

    private final String name;
    private final String email;
    private final int age;

    private User(Entity entity) {
        this.name = entity.getFieldOne();
        this.email = entity.getFieldTwo();
        this.age = entity.getFieldInt();
    }

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    public static User build(String name, String email, int age) {
        BuildConcreteEntity buildConcreteEntity = new BuildConcreteEntity();
        Validator<User> userValidator = new UserValidator();
        Entity userEntity = buildConcreteEntity.buildCustomEntity(name, email, age, userValidator);
        return new User(userEntity);
    }

    @Override
    public int compareTo(User o) {
        if (!this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        } else if (!this.email.equals(o.email)) {
            return this.email.compareTo(o.email);
        } else {
            return Integer.compare(this.age, o.age);
        }
    }

    @Override
    public String toString() {
        return String.format("User{name: %s, email: %s, age: %d}", this.name, this.email, this.age);
    }

    public static Builder builder() {
        return new User.Builder();
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;

        public Builder() {
        }

        public Builder name(String name) {
            checkedStringOnEmpty(name);
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            checkedStringOnEmpty(email);
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            checkedZero(age);
            this.age = age;
            return this;
        }

        public User build(Validator<User> validator) {
            validator.validate(name, email, age);
            return new User(this);
        }
    }
}
