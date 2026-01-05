package ru.aston.finalproject.service;

import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.service.loader.DataLoader;
import ru.aston.finalproject.service.loader.RandomUserDataLoader;
import ru.aston.finalproject.service.loader.UserConsoleDataLoader;
import ru.aston.finalproject.service.loader.UserFileDataLoader;
import ru.aston.finalproject.util.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    public final static String LOAD_FROM_FILE = "-file";
    public final static String LOAD_FROM_CONSOLE = "-console";
    public final static String LOAD_RANDOM = "-random";
    private final Map<String, DataLoader<User>> loaders = new HashMap<>();

    public UserService(UserFileDataLoader userFileDataLoader,
                       UserConsoleDataLoader userConsoleDataLoader,
                       RandomUserDataLoader randomUserDataLoader) {
        loaders.put(LOAD_FROM_FILE, userFileDataLoader);
        loaders.put(LOAD_FROM_CONSOLE, userConsoleDataLoader);
        loaders.put(LOAD_RANDOM, randomUserDataLoader);
    }

    public List<User> loadUsers(String loaderKey, Integer size) {
        return loaders.get(loaderKey).loadEntityList(size);
    }

    public void setLoaderFilePath(String filePath) {
        ((UserFileDataLoader) loaders.get(Key.LOAD_FROM_FILE)).setFilePath(filePath);
    }
}