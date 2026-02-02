package ru.aston.finalproject.service.filters;

import ru.aston.finalproject.entity.user.User;

import java.util.List;

public class FilterListUsers implements FilterList<User> {

    public List<User> filter(List<User> list, Double... value) {
        return list;
    }
}
