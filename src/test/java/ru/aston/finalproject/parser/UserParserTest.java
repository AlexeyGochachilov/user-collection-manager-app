package ru.aston.finalproject.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.finalproject.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserParserTest {

    private Parsing<User> userParser;
    private User user;
    private String toString;

    @BeforeEach
    void initAll() {
        userParser = new UserParser();
        user = User.builder().
                setName("User").
                setEmail("email@mail.ru").
                setAge(20).
                build();
        toString = userParser.parseToString(user);
    }

    @Test
    public void testParseToString() {
        assertEquals("User : email@mail.ru : 20", toString);
    }

    @Test
    public void testParse() {
        User defferentUser = userParser.parse("uSer : eMaIl@MaIl.rU : 20");
        assertEquals(user, defferentUser);
    }

    @Test
    public void testParseWithDelimiter() {
        User defferentUser = userParser.parse("uSer/eMaIl@MaIl.rU/20", "/");
        assertEquals(user, defferentUser);
    }
}
