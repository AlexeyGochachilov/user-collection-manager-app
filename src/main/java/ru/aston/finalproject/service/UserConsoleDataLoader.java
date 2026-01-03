package ru.aston.finalproject.service;

import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.parser.UserParser;

public class UserConsoleDataLoader extends ConsoleDataLoader<User> {
    public UserConsoleDataLoader(UserParser parser) {
        super(parser);
    }
}