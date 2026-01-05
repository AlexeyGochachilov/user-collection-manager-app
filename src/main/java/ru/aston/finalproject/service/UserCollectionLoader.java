package ru.aston.finalproject.service;

import ru.aston.finalproject.workwithuser.User;

import java.util.Collection;

public interface UserCollectionLoader {

    Collection<User> loadUserCollection(Integer size);
}
