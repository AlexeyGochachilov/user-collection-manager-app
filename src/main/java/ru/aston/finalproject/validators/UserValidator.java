package ru.aston.finalproject.validators;

import static ru.aston.finalproject.constants.ConstantMethods.checkedAge;
import static ru.aston.finalproject.constants.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.constants.ConstantMethods.checkedName;

public class UserValidator implements Validator {

    public void validateEntity(String username, String email, int age) {
        checkedName(username);
        checkedEmail(email);
        checkedAge(age);
    }
}
