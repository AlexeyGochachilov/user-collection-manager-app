package ru.aston.finalproject.service.loader;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.parser.UserParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsoleDataLoaderTest {
    @Mock
    private UserParser userParser;
    @Mock
    private AppRequest appRequest;
    @InjectMocks
    private ConsoleDataLoader<User> consoleDataLoader;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .setAge(3)
                .setEmail("test@mail.ru")
                .setName("Name")
                .build();

    }

    @AfterEach
    void tearDown() throws IOException {

    }

    @SneakyThrows
    @Test
    void loadEntityList() {
        int expected = 10;
        when(userParser.parse(any())).thenReturn(user);
        String consoleInput = Stream.generate(() -> "Name test@mail.ru 3").limit(expected).collect(Collectors.joining("\n"));
        System.setIn(new ByteArrayInputStream(consoleInput.getBytes()));

        List<User> list = consoleDataLoader.loadEntityList(expected, appRequest).toList();

        assertEquals(expected, list.size());
    }

//    @Test
//    void loadEntityList() {
//    }
//
//    @Test
//    void loadEntityList() {
//    }
//
//    @Test
//    void loadEntityList() {
//    }
//
//    @Test
//    void loadEntityList() {
//    }
}