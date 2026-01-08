package ru.aston.finalproject.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    private User user;

    @BeforeEach
    void initAll() {
        user = User.builder().
                setName("Ivan").
                setEmail("email@mail.ru").
                setAge(20).
                build();
    }

    @Test
    public void testBuilderUser() {
        assertNotNull(user);
    }

    @Test
    public void testSetName() {
        assertEquals("Ivan", user.getName());
    }

    @Test
    public void testSetEmail() {
        assertEquals("email@mail.ru", user.getEmail());
    }

    @Test
    public void testSetAge() {
        assertEquals(20, user.getAge());
    }
}
