package ru.aston.finalproject.entity;

public class BuildUser {

    public User capitalizeNameAndNormalizedEmail(String name, String email, int age) {
        String normalizedEmail = email.toLowerCase();
        String capitalizedName = capitalize(name);
        return User.builder().setName(capitalizedName).setEmail(normalizedEmail).setAge(age).build();
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
