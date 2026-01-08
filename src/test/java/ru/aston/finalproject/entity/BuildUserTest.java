package ru.aston.finalproject.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildUserTest {

    private User user;

    @BeforeEach
    void initAll() {
        BuildUser buildUser = new BuildUser();
        user = buildUser.capitalizeNameAndNormalizedEmail("ivAn", "eMaIl@maIl.ru", 20);
    }

    @Test
    public void testCapitalizeName() {
        assertEquals("Ivan", user.getName());
    }

    @Test
    public void testNormalizedEmail() {
        assertEquals("email@mail.ru", user.getEmail());
    }
}
