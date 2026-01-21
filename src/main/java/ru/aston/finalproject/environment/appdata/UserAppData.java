package ru.aston.finalproject.environment.appdata;

import ru.aston.finalproject.entity.user.User;
import ru.aston.finalproject.parser.UserParser;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.FileDataLoader;
import ru.aston.finalproject.service.loader.LoaderService;
import ru.aston.finalproject.service.loader.UserLoaderService;

public class UserAppData extends AppData<User> {

    private static final UserParser userParser = new UserParser();
    private static final FileDataLoader<User> fileDataLoader = new FileDataLoader<>(userParser);
    private static final ConsoleDataLoader<User> consoleDataLoader = new ConsoleDataLoader<>(userParser);
    private static final LoaderService<User> userLoaderService = new UserLoaderService(
            fileDataLoader, consoleDataLoader, randomUserDataLoader);

    public UserAppData() {
        super(userParser, userLoaderService, fileDataLoader);
    }
}
