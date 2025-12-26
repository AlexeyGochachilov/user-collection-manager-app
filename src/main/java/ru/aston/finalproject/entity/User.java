package ru.aston.finalproject.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public class User {

    private final String name;
    private final String email;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;
        private static final int MIN_AGE = 1;

        public Builder() {
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

        private void validateAge(int age) {
            if (age <= MIN_AGE) {
                throw new IllegalArgumentException(String.format("Age cannot be below %d", MIN_AGE));
            }
        }

        public User build() {
            validateUser();
            return new User(this);
        }

        private void validateUser() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
        }
    }

    @Override
    public String toString() {
        return "User\n{" + "name = " + name + ",\nemail = " + email + ",\nage   = " + age + '}';
    }
}
