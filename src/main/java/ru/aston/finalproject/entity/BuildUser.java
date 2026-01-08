package ru.aston.finalproject.entity;

import static net.datafaker.internal.helper.WordUtils.capitalize;

public class BuildUser {

    public User capitalizeNameAndNormalizedEmail(String name, String email, int age) {
        String normalizedEmail = email.toLowerCase();
        String capitalizedName = capitalize(name);
        return User.builder().setName(capitalizedName).setEmail(normalizedEmail).setAge(age).build();
    }
}
