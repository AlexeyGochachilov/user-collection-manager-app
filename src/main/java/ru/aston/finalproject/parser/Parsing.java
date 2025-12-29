package ru.aston.finalproject.parser;

import ru.aston.finalproject.entity.Entity;

public interface Parsing {

    String parseEntityToString(Entity entity);

    Entity parseStringToEntity(String data);

    Entity parseStringToEntity(String data, String delimiter);

}
