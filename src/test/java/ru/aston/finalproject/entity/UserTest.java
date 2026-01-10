package ru.aston.finalproject.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.finalproject.app.AppException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.aston.finalproject.util.ConstantFields.MAX_AGE;
import static ru.aston.finalproject.util.ConstantFields.MIN_AGE;

public class UserTest {

    private User user;

    @BeforeEach
    void initUser() {
        user = User.builder().
                setName("Ivan").
                setEmail("email@mail.ru").
                setAge(20).
                build();
    }

    @Test
    public void givenMethodOfClass_whenTakeSimpleNameOfMethod_thenReturnSimpleName() {
        String simpleName;

        simpleName = User.builder().getClass().getSimpleName();
        assertEquals("Builder", simpleName);

        simpleName = User.builder().setName("Ivan").getClass().getSimpleName();
        assertEquals("Builder", simpleName);

        simpleName = User.builder().setEmail("email@mail.ru").getClass().getSimpleName();
        assertEquals("Builder", simpleName);

        simpleName = User.builder().setAge(20).getClass().getSimpleName();
        assertEquals("Builder", simpleName);

        simpleName = User.builder().
                setName("Ivan").
                setEmail("email@mail.ru").
                setAge(20).
                build().getClass().getSimpleName();
        assertEquals("User", simpleName);
    }

    @Test
    public void givenInitUser_whenCheckedNotNull_thenReturnUser() {
        assertNotNull(user);
    }

    @Test
    public void givenInitUser_whenGetUserName_thenHaveExpectedName() {
        String userName = user.getName();
        assertEquals("Ivan", userName);
    }

    @Test
    public void givenInitUser_whenGetUserEmail_thenHaveExpectedEmail() {
        String userEmail = user.getEmail();
        assertEquals("email@mail.ru", userEmail);
    }

    @Test
    public void givenInitUser_whenGetUserAge_thenHaveExpectedAge() {
        int userAge = user.getAge();
        assertEquals(20, userAge);
    }

    @Test
    public void givenDifferentUserWithoutName_whenCreatException_thenHaveValidExc() {
        Exception exception = assertThrows(AppException.class, () -> {
            User.builder().setEmail("email@mail.ru").setAge(20).build();
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    public void givenDifferentUserWithoutEmail_whenCreatException_thenHaveValidExc() {
        Exception exception = assertThrows(AppException.class, () -> {
            User.builder().setName("Ivan").setAge(20).build();
        });
        assertEquals("email cannot be empty", exception.getMessage());
    }

    @Test
    public void givenDifferentUserWithoutAge_whenCreatException_thenHaveValidExc() {
        Exception exception = assertThrows(AppException.class, () -> {
            User.builder().setName("Ivan").setEmail("email@mail.ru").build();
        });
        assertEquals("age cannot be empty", exception.getMessage());
    }

    @Test
    public void givenDifferentUserWithAgeLessMinAge_whenCreatException_thenHaveValidExc() {

        int setAge = -5;
        Exception exception = assertThrows(AppException.class, () -> {
            User.builder().setName("Ivan").setEmail("email@mail.ru").setAge(setAge).build();
        });
        assertEquals(String.format("age cannot be below %d: %d", MIN_AGE, setAge), exception.getMessage());
    }

    @Test
    public void givenDifferentUserWithAgeMoreMaxAge_whenCreatException_thenHaveValidExc() {

        int setAge = 121;
        Exception exception = assertThrows(AppException.class, () -> {
            User.builder().setName("Ivan").setEmail("email@mail.ru").setAge(setAge).build();
        });
        assertEquals(String.format("age cannot be above %d: %d", MAX_AGE, setAge), exception.getMessage());
    }

    @Test
    public void givenUser_whenCallUserToString_thenHaveExpectedString() {
        String toString = user.toString();
        assertEquals("User{name: Ivan, email: email@mail.ru, age: 20}", toString);
    }

    @Test
    public void givenListWithTwoDifferentUsers_whenSortListWithComparable_thenHaveResaltOfCompareTo() {
        List<User> userList;
        User userSecond = User.builder().setName("Max").setEmail("email@mail.ru").setAge(20).build();
        userList = sortUsersWithComparable(user, userSecond);
        assertEquals("Ivan", userList.get(0).getName());

        userSecond = User.builder().setName("Ivan").setEmail("gmail@mail.ru").setAge(20).build();
        userList = sortUsersWithComparable(user, userSecond);
        assertEquals("email@mail.ru", userList.get(0).getEmail());

        userSecond = User.builder().setName("Ivan").setEmail("email@mail.ru").setAge(21).build();
        userList = sortUsersWithComparable(user, userSecond);
        assertEquals(20, userList.get(0).getAge());
    }

    private List<User> sortUsersWithComparable(User userFirst, User userSecond) {
        List<User> userList = Arrays.asList(userFirst, userSecond);
        Collections.sort(userList);
        return userList;
    }
}
