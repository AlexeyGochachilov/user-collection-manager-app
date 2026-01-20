package ru.aston.finalproject.entity.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.finalproject.environment.AppException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    UserValidator userValidator;
    String name;
    String email;
    int age;

    @BeforeEach
    void setUp() {
        userValidator = new UserValidator();
        name = "Ivan";
        email = "m@m.ru";
        age = 20;
    }

    @Test
    public void givenNameAndAge_whenValidate_thenThrowsException() {
        ClassCastException exception = assertThrows(ClassCastException.class, () -> {
            userValidator.validate(name, age);
        });
        assertTrue(exception.getMessage().contains("not"));
    }

    @Test
    public void givenEmailAndAge_whenValidate_thenThrowsException() {
        AppException exception = assertThrows(AppException.class, () -> {
            userValidator.validate(email, age);
        });
        assertTrue(exception.getMessage().contains("Name"));
    }

    @Test
    public void givenOnlyName_whenValidate_thenThrowsException() {
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            userValidator.validate(name);
        });
        assertTrue(exception.getMessage().contains("out of bounds"));
    }

    @Test
    public void givenNameAndEmail_whenValidate_thenThrowsException() {
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            String name = "Ivan";
            userValidator.validate(name, email);
        });
        assertTrue(exception.getMessage().contains("out of bounds"));
    }
}
