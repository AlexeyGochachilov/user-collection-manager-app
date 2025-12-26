package ru.aston.finalproject.entity;

public class BuildConcreteUser {

    public User buildConcreteUser(String name, String email, int age) {
        return User.builder().name(name).email(email).age(age).build();
    }
}
