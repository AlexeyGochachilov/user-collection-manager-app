package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Entity;

public interface Parsing {

    Entity parseStringToEntity(String data, String delimiter);

    Entity parseStringToEntity(String data);

    String parseEntityToString(Entity entity);
}
