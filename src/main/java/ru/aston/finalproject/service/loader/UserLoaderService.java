package ru.aston.finalproject.service.loader;

import ru.aston.finalproject.entity.user.User;

public class UserLoaderService extends LoaderService<User> {
    public static final String LOAD_FROM_FILE = "file";
    public static final String LOAD_FROM_CONSOLE = "console";
    public static final String LOAD_RANDOM = "random";

    public UserLoaderService(FileDataLoader<User> userFileDataLoader,
                             ConsoleDataLoader<User> userConsoleDataLoader,
                             RandomUserDataLoader randomUserDataLoader) {
        addLoader(LOAD_FROM_FILE, userFileDataLoader);
        addLoader(LOAD_FROM_CONSOLE, userConsoleDataLoader);
        addLoader(LOAD_RANDOM, randomUserDataLoader);
    }

}