package ru.aston.finalproject.service;

import ru.aston.finalproject.user.User;

import java.util.Collection;

public interface UserCollectionLoader {

    Collection<User> loadUserCollection(Integer size);
}
