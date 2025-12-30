package ru.aston.finalproject.service;

import ru.aston.finalproject.entity.Entity;

import java.util.Collection;

public interface UserCollectionLoader {

    Collection<Entity> loadUserCollection(Integer size);
}
