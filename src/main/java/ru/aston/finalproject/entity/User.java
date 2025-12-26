package ru.aston.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

import static ru.aston.finalproject.constants.ConstantMethods.validateAge;

@Getter
@EqualsAndHashCode
public class User implements Comparable<User> {

    private final String name;
    private final String email;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "User\n{" + "name = " + name + ",\nemail = " + email + ",\nage   = " + age + '}';
    }

    @Override
    public int compareTo(User o) {
        if (!this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        } else if (!this.email.equals(o.email)) {
            return this.email.compareTo(o.email);
        } else  {
            return Integer.compare(this.age, o.age);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final UserValidator validator;
        private String name;
        private String email;
        private int age;

        public Builder() {
            validator = new UserValidator();
        }

        public Builder name(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            return this;
        }

        public Builder email(String email) {
            this.email = Objects.requireNonNull(email, "Email cannot be null");
            return this;
        }

        public Builder age(int age) {
            validateAge(age);
            this.age = age;
            return this;
        }

        public User build() {
            validator.validateUser(name, email, age);
            return new User(this);
        }
    }
}