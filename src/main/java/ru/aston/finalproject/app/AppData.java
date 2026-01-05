package ru.aston.finalproject.app;

import lombok.Getter;
import lombok.Setter;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.parser.UserParser;
import ru.aston.finalproject.service.loader.UserLoaderService;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.FileDataLoader;
import ru.aston.finalproject.service.loader.RandomUserDataLoader;
import ru.aston.finalproject.validators.UserValidator;

import java.util.List;

@Setter
@Getter
public class AppData {
    private final UserValidator userValidator = new UserValidator();
    private final UserParser userParser = new UserParser();
    private final FileDataLoader<User> userFileDataLoader = new FileDataLoader<>(userParser);
    private final ConsoleDataLoader<User> userConsoleDataLoader = new ConsoleDataLoader<>(userParser);
    private final RandomUserDataLoader randomUserDataLoader = new RandomUserDataLoader(userValidator);
    private final UserLoaderService userService = new UserLoaderService(userFileDataLoader, userConsoleDataLoader, randomUserDataLoader);

    private List<User> userList;

    // Этот класс хранит все переменные приложения.
    // Текущее наполнение - для демонстрации. Класс открыт для изменения.
}
