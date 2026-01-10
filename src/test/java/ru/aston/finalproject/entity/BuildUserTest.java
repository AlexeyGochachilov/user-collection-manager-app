package ru.aston.finalproject.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.finalproject.app.AppException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.aston.finalproject.util.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.util.ConstantFields.MIN_AGE;

public class BuildUserTest {

    private User user;
    private BuildUser buildUser;

    @BeforeEach
    public void setUp() {
        buildUser = new BuildUser();
    }

    @Test
    public void givenUser_whenBuildUserWithCapitalizeNameAndNormalizedEmail_thenHaveExpectedName() {
        user = buildUser.capitalizeNameAndNormalizedEmail("iVAN", "email@mail.ru", 20);
        assertEquals("Ivan", user.getName());
    }

    @Test
    public void givenUser_whenBuildUserWithCapitalizeNameAndNormalizedEmail_thenHaveExpectedEmail() {
        user = buildUser.capitalizeNameAndNormalizedEmail("Ivan", "eMaiL@MaIl.RU", 20);
        assertEquals("email@mail.ru", user.getEmail());
    }

    @Test
    public void givenUser_whenBuildUserWithEmptyName_thenHaveValidExc() {
        String emptyName = " ";
        Exception exception = assertThrows(AppException.class, () -> {
            buildUser.capitalizeNameAndNormalizedEmail(emptyName, "email@mail.ru", 20);
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    public void givenUser_whenBuildUserWithStrangeName_thenHaveValidExc() {
        String strangeName = "I1v3a6n4";
        Exception exception = assertThrows(AppException.class, () -> {
            buildUser.capitalizeNameAndNormalizedEmail(strangeName, "email@mail.ru", 20);
        });
        assertEquals((String.format("%s is not a valid name", strangeName)), exception.getMessage());
    }

    @Test
    public void givenUser_whenBuildUserWithBadEmail_thenHaveValidExc() {
        String email = "i1v3a6n4";
        Exception exception = assertThrows(AppException.class, () -> {
            buildUser.capitalizeNameAndNormalizedEmail("Ivan", email, 20);
        });
        assertEquals(String.format("Invalid email %s", email), exception.getMessage());
    }

    @Test
    public void givenUser_whenBuildUserWithEmptyEmail_thenHaveValidExc() {
        String emptyEmail = " ";
        Exception exception = assertThrows(AppException.class, () -> {
            buildUser.capitalizeNameAndNormalizedEmail("Ivan", emptyEmail, 20);
        });
        assertEquals("email cannot be empty", exception.getMessage());
    }

    @Test
    public void givenUser_whenBuildUserWithAgeLessMinAge_thenHaveValidExc() {
        int setAge = -5;
        Exception exception = assertThrows(AppException.class, () -> {
            buildUser.capitalizeNameAndNormalizedEmail("Ivan", "email@mail.ru", setAge);
        });
        assertEquals(String.format("age cannot be below %d: %d", MIN_AGE, setAge), exception.getMessage());
    }

    @Test
    public void givenUser_whenBuildUserWithAgeMoreMaxAge_thenHaveValidExc() {
        int setAge = 121;
        Exception exception = assertThrows(AppException.class, () -> {
            buildUser.capitalizeNameAndNormalizedEmail("Ivan", "email@mail.ru", setAge);
        });
        assertEquals(String.format("age cannot be above %d: %d", MAX_AGE, setAge), exception.getMessage());
    }
}
