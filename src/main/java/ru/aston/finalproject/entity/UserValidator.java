package ru.aston.finalproject.entity;

import static ru.aston.finalproject.constants.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.validateAge;

public class UserValidator {

    public void validateUser (String username, String email, int age){
        checkedStringOnEmpty(username);
        checkedStringOnEmpty(email);
        checkedEmail(email);
        validateAge(age);
    }
}
