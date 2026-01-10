package ru.aston.finalproject.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuildUserTest {

    private BuildUser buildUser;
    private User user;

    @BeforeEach
    public void setUp() {
        buildUser = new BuildUser();
    }

    @Nested
    class BuildUserCreationTests {

        @Test
        void givenValidData_whenBuildUserUsedMethod_thenUserCreatedSuccessfully() {
            user = buildUser.capitalizeNameAndNormalizedEmail("Ivan", "email@mail.ru", 20);

            assertNotNull(user);
            assertEquals("Ivan", user.getName());
            assertEquals("email@mail.ru", user.getEmail());
            assertEquals(20, user.getAge());
        }
    }

    @Nested
    class CapitalizeName {

        private String invalidName;

        @Test
        void givenLowerCaseName_whenBuildUser_thenCorrectCaseName() {
            invalidName = "ivan";
            user = buildUser.capitalizeNameAndNormalizedEmail(invalidName, "email@mail.ru", 20);
            assertEquals("Ivan", user.getName());
        }


        @Test
        void givenDifferentCaseName_whenBuildUser_thenCorrectCaseName() {
            invalidName = "iVaN";
            user = buildUser.capitalizeNameAndNormalizedEmail(invalidName, "email@mail.ru", 20);
            assertEquals("Ivan", user.getName());
        }

        @Test
        void givenUpperCaseName_whenBuildUser_thenCorrectCaseName() {
            invalidName = "IVAN";
            user = buildUser.capitalizeNameAndNormalizedEmail(invalidName, "email@mail.ru", 20);
            assertEquals("Ivan", user.getName());
        }
    }

    @Nested
    class NormalizedEmail {

        private String invalidEmail;

        @Test
        public void givenUpperCaseEmail_whenBuildUser_thenCorrectCaseEmail() {
            invalidEmail = "EMAIL@MAIL.RU";
            user = buildUser.capitalizeNameAndNormalizedEmail("Ivan", invalidEmail, 20);
            assertEquals("email@mail.ru", user.getEmail());
        }

        @Test
        public void givenDifferentCaseEmail_whenBuildUser_thenCorrectCaseEmail() {
            invalidEmail = "EmAiL@mAiL.Ru";
            user = buildUser.capitalizeNameAndNormalizedEmail("Ivan", invalidEmail, 20);
            assertEquals("email@mail.ru", user.getEmail());
        }
    }

}