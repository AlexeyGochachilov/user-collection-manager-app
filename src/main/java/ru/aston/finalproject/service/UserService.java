package ru.aston.finalproject.service;

import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.util.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<String, DataLoader<User>> loaders = new HashMap<>();

    public UserService(UserFileDataLoader userFileDataLoader,
                       UserConsoleDataLoader userConsoleDataLoader,
                       RandomUserDataLoader randomUserDataLoader) {
        loaders.put(Key.LOAD_FROM_FILE, userFileDataLoader);
        loaders.put(Key.LOAD_FROM_CONSOLE, userConsoleDataLoader);
        loaders.put(Key.LOAD_RANDOM, randomUserDataLoader);
    }

    public List<User> loadUsers(String loaderKey, Integer size) {
        return loaders.get(loaderKey).loadEntityList(size);
    }

    public void setLoaderFilePath(String filePath) {
        ((UserFileDataLoader) loaders.get(Key.LOAD_FROM_FILE)).setFilePath(filePath);
    }
}