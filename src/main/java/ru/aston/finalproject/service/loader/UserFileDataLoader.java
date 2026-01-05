package ru.aston.finalproject.service.loader;

import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.parser.UserParser;

public class UserFileDataLoader extends FileDataLoader<User>{

    public UserFileDataLoader(UserParser parser) {
        super(parser);
    }
}