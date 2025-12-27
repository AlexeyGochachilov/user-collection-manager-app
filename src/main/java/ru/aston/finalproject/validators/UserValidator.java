package ru.aston.finalproject.validators;

import static ru.aston.finalproject.constants.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.constants.ConstantMethods.checkedAge;

public class UserValidator implements Validator {

    public void validateEntity (String username, String email, int age){
        checkedStringOnEmpty(username);
        checkedEmail(email);
        checkedAge(age);
    }
}
