package ru.aston.finalproject.workwithuser;

public class BuildUser {

    public User buildUser(String name, String email, int age) {
        return User.builder().setName(name).setEmail(email).setAge(age).build();
    }
}
