package ru.aston.finalproject.service.loader;

import ru.aston.finalproject.entity.User;

public class UserLoaderService extends LoaderService<User> {
    public final static String LOAD_FROM_FILE = "file";
    public final static String LOAD_FROM_CONSOLE = "console";
    public final static String LOAD_RANDOM = "random";

    public UserLoaderService(FileDataLoader<User> userFileDataLoader,
                             ConsoleDataLoader<User> userConsoleDataLoader,
                             RandomUserDataLoader randomUserDataLoader) {
        addLoader(LOAD_FROM_FILE, userFileDataLoader);
        addLoader(LOAD_FROM_CONSOLE, userConsoleDataLoader);
        addLoader(LOAD_RANDOM, randomUserDataLoader);
    }

}