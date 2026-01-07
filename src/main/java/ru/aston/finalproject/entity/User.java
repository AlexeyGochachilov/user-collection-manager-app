package ru.aston.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

import static ru.aston.finalproject.util.ConstantMethods.checkedAge;
import static ru.aston.finalproject.util.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.util.ConstantMethods.checkedName;

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
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            validate();
            return new User(this);
        }

        private void validate() {
            checkedName(name);
            checkedEmail(email);
            checkedAge(age);
        }
    }
}
