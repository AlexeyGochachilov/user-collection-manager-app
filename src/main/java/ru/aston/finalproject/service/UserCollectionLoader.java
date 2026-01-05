package ru.aston.finalproject.service;

import ru.aston.finalproject.entity.User;

import java.util.Collection;

public interface UserCollectionLoader {

    Collection<User> loadUserCollection(Integer size);
}
