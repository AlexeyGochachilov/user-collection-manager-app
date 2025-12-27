package ru.aston.finalproject.BuildsEntitys;

import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.validators.UserValidator;

public class BuildUser {

    private final UserValidator validator =  new UserValidator();

    public Entity buildConcreteUser(String name, String email, int age) {
        return Entity.
                builder().
                fieldOne(name).
                fieldTwo(email).
                fieldInt(age).
                build(validator);
    }
}
