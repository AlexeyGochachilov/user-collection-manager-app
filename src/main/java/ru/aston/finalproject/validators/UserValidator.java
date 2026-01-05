package ru.aston.finalproject.validators;

import ru.aston.finalproject.workwithentity.User;

import static ru.aston.finalproject.constants.ConstantMethods.checkedAge;
import static ru.aston.finalproject.constants.ConstantMethods.checkedEmail;
import static ru.aston.finalproject.constants.ConstantMethods.checkedName;

public class UserValidator implements Validator<User> {

    public void validate(String name, String email, int age) {
        checkedName(name);
        checkedEmail(email);
        checkedAge(age);
    }
}
